package com.dao.imp;

import org.springframework.stereotype.Repository;

import com.dao.IAuthorityDao;
import com.entities.Authority;
import com.gnericdao.imp.HibernateGenericDaoImp;

@Repository
public class AuthorityDaoImp extends HibernateGenericDaoImp<Authority, Long> implements IAuthorityDao {

	public AuthorityDaoImp() {
		super(Authority.class);
	}
}
