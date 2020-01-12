package com.rest.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exceptions.EmailAlreadyExistsException;
import com.exceptions.InvalidTokenException;
import com.exceptions.UsernameAlreadyExistsException;
import com.gnericdao.exceptions.EntityNotFoundException;

@ControllerAdvice
public class UserRestExceptionsHandler {
	
	@ExceptionHandler
	public ResponseEntity<ClientErrorResponse> handleException(EntityNotFoundException e) {
		
		ClientErrorResponse error = new ClientErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<ClientErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ClientErrorResponse> handleException(UsernameAlreadyExistsException e) {
		
		ClientErrorResponse error = new ClientErrorResponse();
		
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<ClientErrorResponse>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler
	public ResponseEntity<ClientErrorResponse> handleException(EmailAlreadyExistsException e) {
		
		ClientErrorResponse error = new ClientErrorResponse();
		
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<ClientErrorResponse>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler
	public ResponseEntity<ClientErrorResponse> handleException(InvalidTokenException e) {
		
		ClientErrorResponse error = new ClientErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<ClientErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<?> handleException(AuthenticationException e) {
		
		ClientErrorResponse error = new ClientErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<ClientErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	/*
	@ExceptionHandler
	public ResponseEntity<ClientErrorResponse> handleException(Exception e) {
		
		ClientErrorResponse error = new ClientErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("You seem to send an invalid request.");
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<ClientErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	*/
}
