package com.services;

import com.entities.Authority;
import com.gnericdao.exceptions.EntityNotFoundException;
import com.java.utils.AuthorityType;

public interface IAuthorityService {
	
	public Authority getAuthority(AuthorityType authority) throws EntityNotFoundException;
	
}
