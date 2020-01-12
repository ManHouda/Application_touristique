package com.services.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.IVerificationTokenDao;
import com.entities.VerificationToken;
import com.services.IVerificationTokenService;

@Service
public class VerificationTokenServiceImp implements IVerificationTokenService {

	@Autowired
	IVerificationTokenDao verificationTokenDao;
	
	@Transactional
	public VerificationToken getVerificationToken(String token) {
		
		List<VerificationToken> tokens = verificationTokenDao.getEntityByColumnValue("token", token);
		
		if(tokens != null && tokens.size() > 0) {
			return tokens.get(0);
		}
		
		return null;
	}

}
