package com.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

	public EmailAlreadyExistsException() {
		super();
	}

	public EmailAlreadyExistsException(String msg) {
		super(msg);
	}

	public EmailAlreadyExistsException(Throwable t) {
		super(t);
	}

	public EmailAlreadyExistsException(String msg, Throwable t) {
		super(msg, t);
	}
}
