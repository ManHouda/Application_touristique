package com.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
	
	private String token;
	
	private String type = "Bearer ";
	
	private String username;
	
	private Collection<? extends GrantedAuthority> authortities;

	public JwtResponse(String token, String username, Collection<? extends GrantedAuthority> authortities) {
		this.token = token;
		this.username = username;
		this.authortities = authortities;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<? extends GrantedAuthority> getAuthortities() {
		return authortities;
	}

	public void setAuthortities(Collection<? extends GrantedAuthority> authortities) {
		this.authortities = authortities;
	}
}
