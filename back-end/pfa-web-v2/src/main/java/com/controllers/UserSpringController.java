package com.controllers;

import java.util.Calendar;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.entities.User;
import com.entities.VerificationToken;
import com.events.OnRegistrationSuccessEvent;
import com.exceptions.EmailAlreadyExistsException;
import com.exceptions.UsernameAlreadyExistsException;
import com.gnericdao.exceptions.EntityNotFoundException;
import com.java.utils.AuthorityType;
import com.services.IAuthorityService;
import com.services.IUserService;
import com.services.IVerificationTokenService;
import com.sun.istack.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserSpringController {

	private static Logger logger = Logger.getLogger(UserSpringController.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	HttpServletRequest request;

	@Autowired
	private IVerificationTokenService verificationTokenService;

	@Autowired
	private IAuthorityService authorityService;

	@Autowired
	private IUserService userService;

	@GetMapping("/register")
	public String registerNewUser(WebRequest request) {

		User user = new User("mohrachi", "mohrachimohammed@gmail.com", passwordEncoder.encode("mohrachi"));

		try {

			user.addAuthoriry(authorityService.getAuthority(AuthorityType.ROLE_ADMIN));

			logger.info("regestring new user with username " + user.getUsername() + " and authority "
					+ user.getAuthorities().toString());

		} catch (EntityNotFoundException e) {

			logger.info(e.getMessage());
		}

		try {

			userService.registerNewUserAccount(user);

		} catch (UsernameAlreadyExistsException e) {

			logger.info(e.getMessage());

		} catch (EmailAlreadyExistsException e) {

			logger.info(e.getMessage());
		}

		String appUrl = request.getContextPath();

		logger.info("app url >>> " + appUrl);
		logger.info("publishing OnRegistrationSuccessEvent");

		eventPublisher.publishEvent(new OnRegistrationSuccessEvent(user, appUrl));

		// autoAuthentication(context, user.getUsername(), "bilal");

		logger.info("registration process completed for: " + user.getUsername());

		return "registrationSuccess";
	}

	@GetMapping("/confirmRegistration")
	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {

		VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);

		if (verificationToken == null) {
			return "redirect:access-denied";
		}

		User user = verificationToken.getUser();

		Calendar calendar = Calendar.getInstance();

		// if the limit time has exceeded
		if ((verificationToken.getExpirationDate().getTime() - calendar.getTime().getTime()) <= 0) {
			return "redirect:access-denied";
		}

		user.setEnabled(true);

		userService.enableRegisteredUser(user);

		return "registrationConfirmed";
	}

	@GetMapping("/deleteUser")
	public String deleteUser() {

		// Get the user from the context
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// Get the username
		String username = "";

		if (principal instanceof UserDetails) {

			username = ((UserDetails) principal).getUsername();

		} else {

			username = principal.toString();
		}

		// log out the user
		System.out.println("Going to delete " + username);
		logger.info("Going to delete " + username);

		try {

			request.logout();

		} catch (ServletException e) {

			logger.warning("Couldn't log the user " + username + "out");
		}

		try {

			userService.deleteUserByUsername(username);

		} catch (EntityNotFoundException e) {

			logger.log(Level.WARNING,
					"You are trying to delete user with username " + username + " which doesn't exist");
		}

		return "redirect:/";
	}

	@GetMapping("/showLoginPage")
	public String showLoginPage() {
		return "login";
	}

	@GetMapping("/accessDenied")
	public String accessDenied() {

		System.out.println("Your access is denied, sorry!");

		return "access-denied";
	}

	@GetMapping("/")
	public String main() {
		return "welcome";
	}

	public void autoAuthentication(HttpServletRequest request, String username, String password) {
		try {
			request.login(username, password);
		} catch (ServletException e) {
			System.out.println("We couldn't automatically log in.");
		}
	}

}
