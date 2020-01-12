package com.services.imp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.IAuthorityDao;
import com.entities.Authority;
import com.gnericdao.exceptions.EntityNotFoundException;
import com.java.utils.AuthorityType;
import com.services.IAuthorityService;

@Service
public class AuthorityServiceImp implements IAuthorityService {
	
	@Autowired
	IAuthorityDao authorityDao;
	
	@Transactional
	public Authority getAuthority(AuthorityType authority) throws EntityNotFoundException {
		
		if(authority == AuthorityType.ROLE_TOURIST) {
			return authorityDao.find(Long.valueOf(2));
		}
		
		if(authority == AuthorityType.ROLE_ADMIN) {
			return authorityDao.find(Long.valueOf(1));
		}
		
		return null;
	}
}
