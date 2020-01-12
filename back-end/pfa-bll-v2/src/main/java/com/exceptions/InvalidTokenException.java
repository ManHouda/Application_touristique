package com.exceptions;

public class InvalidTokenException extends RuntimeException {

	public InvalidTokenException() {
	}

	public InvalidTokenException(String message) {
		super(message);
	}

	public InvalidTokenException(Throwable cause) {
		super(cause);
	}

	public InvalidTokenException(String message, Throwable cause) {
		super(message, cause);
	}
}
