package com.events;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.entities.User;
import com.exceptions.SendingEmailFailureException;
import com.services.IUserService;

@Component
public class RegistrationEmailListener implements ApplicationListener<OnRegistrationSuccessEvent> {

	@Autowired
	private IUserService userService;

	@Autowired
	private MailSender mailSender;

	private static Logger logger = Logger.getLogger(RegistrationEmailListener.class.getName());

	public void onApplicationEvent(OnRegistrationSuccessEvent event) {

		logger.info("The registraton success event is fired up.");

		try {

			this.confirmRegistration(event);

		} catch (SendingEmailFailureException e) {

			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private void confirmRegistration(OnRegistrationSuccessEvent event) throws SendingEmailFailureException {

		User user = event.getUser();

		String token = UUID.randomUUID().toString();

		userService.createVerficationToken(user, token);

		String recipent = user.getEmail();

		String subject = "Registration confirmation";

		String url = event.getAppUrl() + "/api/all/confirm-registration?token=" + token;

		String message = "Thank you for regestring " + user.getUsername()
				+ " in our Tourists web app. Please click on the link below to complete your registration: ";

		SimpleMailMessage email = new SimpleMailMessage();

		email.setTo(recipent);

		email.setSubject(subject);

		email.setText(message + "http://localhost:8080" + url);

		mailSender.send(email);

		logger.info("Registration >>> Activation email is sent");
		logger.info("Recipient >>> " + recipent);
		logger.info("Text >>> " + email.getText());
	}
}
