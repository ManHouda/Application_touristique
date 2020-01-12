package com.dao.imp;

import org.springframework.stereotype.Repository;

import com.dao.ITouristDao;
import com.entities.Tourist;
import com.gnericdao.imp.HibernateGenericDaoImp;

@Repository
public class TouristDaoImp extends HibernateGenericDaoImp<Tourist, Long> implements ITouristDao {

	public TouristDaoImp() {
		super(Tourist.class);
	}
}
