package com.dao.imp;

import org.springframework.stereotype.Repository;

import com.dao.IRegionDao;
import com.entities.Region;
import com.gnericdao.imp.HibernateGenericDaoImp;

@Repository
public class RegionDaoImp extends HibernateGenericDaoImp<Region, Long> implements IRegionDao {

	public RegionDaoImp() {
		super(Region.class);
	}
}
