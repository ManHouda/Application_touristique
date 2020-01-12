package com.dao;

import java.util.List;

import com.entities.Monument;
import com.gnericdao.api.IGenericDao;

public interface IMonumentDao extends IGenericDao<Monument, Long> {
	
	public List<Monument> getMostVisitedMonuments();
	
}
