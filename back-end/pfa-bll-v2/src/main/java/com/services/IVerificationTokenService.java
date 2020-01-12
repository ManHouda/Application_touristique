package com.services;

import com.entities.VerificationToken;

public interface IVerificationTokenService {
	
	public VerificationToken getVerificationToken(String token);
	
}
