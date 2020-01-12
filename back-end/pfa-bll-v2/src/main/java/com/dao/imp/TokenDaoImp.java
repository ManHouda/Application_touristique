package com.dao.imp;

import org.springframework.stereotype.Repository;

import com.dao.IVerificationTokenDao;
import com.entities.VerificationToken;
import com.gnericdao.imp.HibernateGenericDaoImp;

@Repository
public class TokenDaoImp extends HibernateGenericDaoImp<VerificationToken, Long> implements IVerificationTokenDao {

	public TokenDaoImp() {
		super(VerificationToken.class);
	}

}
