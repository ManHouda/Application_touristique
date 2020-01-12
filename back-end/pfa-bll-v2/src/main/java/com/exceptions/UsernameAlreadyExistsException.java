package com.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {

	public UsernameAlreadyExistsException() {
		super();
	}

	public UsernameAlreadyExistsException(String msg) {
		super(msg);
	}

	public UsernameAlreadyExistsException(Throwable t) {
		super(t);
	}

	public UsernameAlreadyExistsException(String msg, Throwable t) {
		super(msg, t);
	}
}
