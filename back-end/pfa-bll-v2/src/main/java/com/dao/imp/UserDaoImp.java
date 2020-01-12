package com.dao.imp;

import org.springframework.stereotype.Repository;

import com.dao.IUserDao;
import com.entities.User;
import com.gnericdao.imp.HibernateGenericDaoImp;

@Repository
public class UserDaoImp extends HibernateGenericDaoImp<User, Long> implements IUserDao {

	public UserDaoImp() {
		super(User.class);
	}
}
