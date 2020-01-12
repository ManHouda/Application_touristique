package com.dao.imp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.IMonumentDao;
import com.entities.Monument;
import com.gnericdao.imp.HibernateGenericDaoImp;

@Repository
public class MonumentDaoImp extends HibernateGenericDaoImp<Monument, Long> implements IMonumentDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public MonumentDaoImp() {
		super(Monument.class);
	}

	public List<Monument> getMostVisitedMonuments() {
		
		String hql = "FROM Monument m ORDER BY m.visitsRate DESC";
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Monument> query = session.createQuery(hql, Monument.class);
		
		List<Monument> monuments = query.getResultList();
		
		return monuments;
		
	}

}