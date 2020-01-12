package com.rest.controllers;

import java.util.Calendar;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.configuration.jwt.JwtTokenUtil;
import com.entities.User;
import com.entities.VerificationToken;
import com.events.OnMessageSentEvent;
import com.events.OnRegistrationSuccessEvent;
import com.exceptions.InvalidTokenException;
import com.java.utils.AuthorityType;
import com.services.IAuthorityService;
import com.services.IUserService;
import com.services.IVerificationTokenService;
import com.utils.JwtResponse;
import com.utils.LoginForm;
import com.utils.MessageForm;

@RestController
@RequestMapping("/api/all")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ApplicationCotroller {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private IVerificationTokenService verificationTokenService;

	@Autowired
	private IAuthorityService authorityService;

	@Autowired
	private IUserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private static Logger logger = Logger.getLogger(ApplicationCotroller.class);

	@PostMapping("/users")
	public User registerNewUser(WebRequest request, @RequestBody User user) {

		System.out.println("Adding new user 2");

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.addAuthoriry(authorityService.getAuthority(AuthorityType.ROLE_TOURIST));

		System.out.println("Adding new user 2");

		// this can fire up 2 exceptions we should handle with a @ControllerAdvice :
		// -> UsernameAlreadyExistsException
		// -> EmailAlreadyExistsException
		userService.registerNewUserAccount(user);

		String appUrl = request.getContextPath();

		eventPublisher.publishEvent(new OnRegistrationSuccessEvent(user, appUrl));

		return user;
	}

	@PostMapping(value = "/signin")
	public ResponseEntity<?> signin(@Valid @RequestBody LoginForm loginForm) throws AuthenticationException {
		
		System.out.println("entered the sign in controller");
		
		System.out.println("authenticating the user: " + loginForm.getUsername());
		System.out.println("with password: " + loginForm.getPassword());
		
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
		
		System.out.println("user authentication is " + authentication.isAuthenticated());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);

		final UserDetails user = userService.loadUserByUsername(loginForm.getUsername());
		
		System.out.println("Generating token for the user: " + user.getUsername());
		
		final String token = jwtTokenUtil.generateToken(user);
		
		System.out.println("token generated");
		
		return ResponseEntity.ok(new JwtResponse(token, user.getUsername(), user.getAuthorities()));
	}

	@GetMapping("/confirm-registration")
	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {

		VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);

		System.out.println("verificationToken: " + verificationToken);

		if (verificationToken == null) {
			throw new InvalidTokenException("That token does not exist.");
		}

		User user = verificationToken.getUser();

		Calendar calendar = Calendar.getInstance();

		// if the limit time has exceeded
		if ((verificationToken.getExpirationDate().getTime() - calendar.getTime().getTime()) <= 0) {
			throw new InvalidTokenException("That token limit age has expired.");
		}

		user.setEnabled(true);

		userService.enableRegisteredUser(user);

		return "success";
	}
	
	@PostMapping("/messages")
	public ResponseEntity<?> sendMsgToAdmin(@Valid @RequestBody MessageForm msg) {

		eventPublisher.publishEvent(new OnMessageSentEvent(msg.getName(), msg.getEmail(), msg.getMessage()));

		return new ResponseEntity<>(msg, HttpStatus.OK);

	}
	
	@GetMapping("/test")
	public ResponseEntity<?> test() {
		return new ResponseEntity<>(new JwtResponse("h", "bilalTest", null), HttpStatus.OK);
	}

}
