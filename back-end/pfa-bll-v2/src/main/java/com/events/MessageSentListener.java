package com.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.exceptions.SendingEmailFailureException;

@Component
public class MessageSentListener implements ApplicationListener<OnMessageSentEvent> {
	
	@Autowired
	private MailSender mailSender;
	
	public void onApplicationEvent(OnMessageSentEvent event) {
		sendEmailToAdmin(event);
	}
	
	private void sendEmailToAdmin(OnMessageSentEvent event) throws SendingEmailFailureException {
				
		String recipent = "bilal1997mars@gmail.com";

		String subject = "Email from user " + event.getName();

		String message = "Email of the user: " + event.getEmail() + "\n";
		
		message += event.getMessage();
		
		SimpleMailMessage email = new SimpleMailMessage();

		email.setTo(recipent);

		email.setSubject(subject);

		email.setText(message);

		mailSender.send(email);
	}
	
}
