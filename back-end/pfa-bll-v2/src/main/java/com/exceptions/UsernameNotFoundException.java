package com.exceptions;

/**
 * checked exception launched when we look for non existion username
 *
 */
public class UsernameNotFoundException extends RuntimeException {

	public UsernameNotFoundException() {
		super();
	}

	public UsernameNotFoundException(String msg) {
		super(msg);
	}

	public UsernameNotFoundException(Throwable t) {
		super(t);
	}

	public UsernameNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
}
