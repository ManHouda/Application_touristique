package com.dao.imp;

import org.springframework.stereotype.Repository;

import com.dao.ICircuitDao;
import com.entities.Circuit;
import com.gnericdao.imp.HibernateGenericDaoImp;

@Repository
public class CircuitDaoImp extends HibernateGenericDaoImp<Circuit, Long> implements ICircuitDao {

	public CircuitDaoImp() {
		super(Circuit.class);
	}
}
