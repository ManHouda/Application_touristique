package com.java.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.entities.Authority;
import com.entities.User;

/**
 * This is where we rap our user details it implements UserDetails so it can be
 * integrated within spring security
 */
public class UserDetailsImp implements UserDetails {

	private User user;

	public UserDetailsImp(User user) {
		this.user = user;
	}
/*
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO

		Collection<Object> authorities = user.getAuthorities().stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getName().toString()))
				.collect(Collectors.toList());

		return user.getAuthorities().stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getName().toString()))
				.collect(Collectors.toList());
		;
	}
*/
	
	public Set<SimpleGrantedAuthority> getAuthorities() {
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		
		for(Authority it : user.getAuthorities()) {
			authorities.add(new SimpleGrantedAuthority(it.getName().toString()));
		}
		
		return authorities;
	}

	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getUsername();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
}
