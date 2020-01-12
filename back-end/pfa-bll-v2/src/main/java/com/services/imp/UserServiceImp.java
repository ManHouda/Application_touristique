package com.services.imp;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dao.IUserDao;
import com.dao.IVerificationTokenDao;
import com.entities.User;
import com.entities.VerificationToken;
import com.exceptions.EmailAlreadyExistsException;
import com.exceptions.UsernameAlreadyExistsException;
import com.gnericdao.exceptions.EntityNotFoundException;
import com.java.utils.UserDetailsImp;
import com.services.IUserService;

/**
 * That is the custom user service as Spring Security enforce by the
 * UserDetailsService implementation
 *
 */
@Service
public class UserServiceImp implements IUserService {

	private static Logger logger = Logger.getLogger(UserServiceImp.class.getName());

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IVerificationTokenDao verificationTokenDao;

	@Transactional
	public UserDetails loadUserByUsername(String username) {

		System.out.println("Retrieving user: " + username);

		User user = userDao.getEntityByColumnValue("username", username).get(0);

		if (user == null) {
			System.out.println("it is null");
			throw new UsernameNotFoundException("This username : " + username + " is not found!");
		}

		return new UserDetailsImp(user);
	}

	@Transactional
	public Long registerNewUserAccount(User user) {

		if (usernameExists(user.getUsername())) {
			throw new UsernameAlreadyExistsException("The username " + user.getUsername() + " is already used.");
		}

		if (emailExists(user.getEmail())) {
			throw new EmailAlreadyExistsException("The email " + user.getEmail() + " has already an account.");
		}
		
		user.setDateCreated(new Date());
		
		return userDao.save(user);
	}

	@Transactional
	public void createVerficationToken(User user, String token) {
		VerificationToken newUserToken = new VerificationToken(user, token);
		verificationTokenDao.save(newUserToken);
	}

	@Transactional
	public void enableRegisteredUser(User user) {
		userDao.update(user);
	}

	@Transactional
	public void deleteUserByUsername(String username) throws EntityNotFoundException {

		logger.info("deleting the user with username: " + username + " ....");

		List<User> users = userDao.getEntityByColumnValue("username", username);

		if (users != null && users.size() > 0) {
			userDao.delete(users.get(0).getId());
		}
	}

	private boolean usernameExists(String username) {
		List<User> users = userDao.getEntityByColumnValue("username", username);

		if (users == null || users.size() == 0) {
			return false;
		}

		return true;
	}

	private boolean emailExists(String email) {
		List<User> users = userDao.getEntityByColumnValue("email", email);

		if (users == null || users.size() == 0) {
			return false;
		}

		return true;
	}
	
	@Transactional
	public User getUser(Long id) {
		return userDao.find(id);
	}
	
	@Transactional
	public void updateUser(User user) {
		userDao.update(user);
	}
}
