package com.controllers;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.services.ITouristService;
import com.services.IUserService;
import com.services.IVerificationTokenService;

@Controller
public class TouristController {

	@Autowired
	private ITouristService touristService;

	@Autowired
	private IUserService userService;

	@Autowired
	private HttpServletRequest context;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private IVerificationTokenService verificationTokenService;
	
//	@Autowired
//	private IAuthorityService authorityService;
	
	private static Logger logger = Logger.getLogger(TouristController.class.getClass());

	@GetMapping("/tourists")
	public String tourists() {

		System.out.println("from the tourists controller!");

		// touristService.saveCustomer(new Tourist("bilal", "ennouali", "email@gmail",
		// "psswd", false));

		System.out.println("done");

		return "redirect:/";
	}

//	@GetMapping("/register")
//	public String registerNewUser(WebRequest request) {
//
//		User user = new User("benamar", "imane.benamar1@gmail.com", passwordEncoder.encode("benamar"), new Date());
//		
//		try {
//			
//			user.addAuthoriry(authorityService.getAuthority(AuthorityType.ROLE_TOURIST));
//			
//		} catch (EntityNotFoundException e) {
//			
//			logger.info(e.getMessage());
//		}
//
//		try {
//
//			userService.registerNewUserAccount(user);
//
//		} catch (UsernameAlreadyExistsException e) {
//
//			logger.info(e.getMessage());
//
//		} catch (EmailAlreadyExistsException e) {
//
//			logger.info(e.getMessage());
//		}
//
//		String appUrl = request.getContextPath();
//		
//		logger.info("app url >>> " + appUrl);
//		logger.info("publishing OnRegistrationSuccessEvent");
//		
//		eventPublisher.publishEvent(new OnRegistrationSuccessEvent(user, appUrl));
//
//		//autoAuthentication(context, user.getUsername(), "bilal");
//		
//		logger.info("registration process completed for: " + user.getUsername());
//		
//		return "registrationSuccess";
//	}

//	@GetMapping("/confirmRegistration")
//	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {
//
//		VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
//
//		if (verificationToken == null) {
//			return "redirect:access-denied";
//		}
//
//		User user = verificationToken.getUser();
//
//		Calendar calendar = Calendar.getInstance();
//		
//		// if the limit time has exceeded
//		if ((verificationToken.getExpirationDate().getTime() - calendar.getTime().getTime()) <= 0) {
//			return "redirect:access-denied";
//		}
//		
//		user.setEnabled(true);
//		
//		userService.enableRegisteredUser(user);
//		
//		return "registrationConfirmed";
//	}

//	public void autoAuthentication(HttpServletRequest request, String username, String password) {
//		try {
//			request.login(username, password);
//		} catch (ServletException e) {
//			System.out.println("We couldn't automatically log in.");
//		}
//	}

	@GetMapping("/admins")
	public String admins() {

		System.out.println("from the admins controller!");

		// touristService.saveCustomer(new Tourist("bilal", "ennouali", "email@gmail",
		// "psswd", false));

		System.out.println("done");

		return "redirect:/";
	}

	@GetMapping("/showLoginPage")
	public String showLoginPage() {
		return "login";
	}

//	@GetMapping("/")
//	public String main() {
//		return "welcome";
//	}
//
//	@GetMapping("/accessDenied")
//	public String accessDenied() {
//
//		System.out.println("Your access is denied, sorry!");
//
//		return "access-denied";
//	}

}
