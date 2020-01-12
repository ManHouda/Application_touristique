package com.rest.controllers;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.configuration.jwt.JwtTokenUtil;
import com.entities.User;
import com.services.IUserService;
import com.sun.istack.logging.Logger;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/users")
	public ResponseEntity<UserDetails> users(@RequestParam("token") String token) {
		
		String username = jwtTokenUtil.getUsernameFromToken(token);
		
		UserDetails user = null;
		
		if(username != null) {
			user = userService.loadUserByUsername(username);
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping("/users")
	public ResponseEntity<User> deleteUser(HttpServletRequest request, @RequestBody User user) {

		String token = request.getHeader("Authorization").replace("Bearer ", "");

		String username = jwtTokenUtil.getUsernameFromToken(token);

		UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

		System.out.println("username from token: " + username);
		System.out.println("username from userDetails: " + userDetails.getUsername());

		System.out.println("password from request body: " + user.getPassword());
		System.out.println("password from userDeatils: " + userDetails.getPassword());

		System.out.println("matches = " + passwordEncoder.matches(user.getPassword(), userDetails.getPassword()));

		if (userDetails != null && username.equals(userDetails.getUsername())
				&& passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {

			userService.deleteUserByUsername(userDetails.getUsername());
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/test")
	public String accessDenied() {
		return "This is a test";
	}

	public void autoAuthentication(HttpServletRequest request, String username, String password) {

		try {

			request.login(username, password);

		} catch (ServletException e) {

			logger.warning("we couldn't automatically log in.");
		}
	}

}
