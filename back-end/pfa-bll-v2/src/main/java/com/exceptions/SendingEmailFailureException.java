package com.exceptions;

public class SendingEmailFailureException extends RuntimeException {

	public SendingEmailFailureException() {
		super();
	}

	public SendingEmailFailureException(String msg) {
		super(msg);
	}

	public SendingEmailFailureException(Throwable t) {
		super(t);
	}

	public SendingEmailFailureException(String msg, Throwable t) {
		super(msg, t);
	}

}
