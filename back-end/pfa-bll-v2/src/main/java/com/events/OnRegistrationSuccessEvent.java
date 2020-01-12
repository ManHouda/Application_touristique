package com.events;

import org.springframework.context.ApplicationEvent;

import com.entities.User;

public class OnRegistrationSuccessEvent extends ApplicationEvent {
	
	private String appUrl;
	
	private User user;
	
	public OnRegistrationSuccessEvent(User user, String appUrl) {
		super(user);
		this.user = user;
		this.appUrl = appUrl;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
