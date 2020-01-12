package com.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.entities.User;

public interface IUserService extends UserDetailsService {

	public Long registerNewUserAccount(User user);

	public void createVerficationToken(User user, String token);

	public void enableRegisteredUser(User user);

	public void deleteUserByUsername(String username);
	
	public User getUser(Long id);
	
	public void updateUser(User user);

}
