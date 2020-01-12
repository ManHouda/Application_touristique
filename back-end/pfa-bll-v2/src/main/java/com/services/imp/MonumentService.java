package com.services.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.IMonumentDao;
import com.entities.Monument;
import com.services.IMonumentService;

@Service
public class MonumentService implements IMonumentService {
	
	@Autowired
	private IMonumentDao monumentDao;
	
	@Transactional
	public Monument getMonument(Long id) {
		return monumentDao.find(id);
	}
	
	@Transactional
	public List<Monument> getAllMonument() {
		return monumentDao.getAll();
	}

	@Transactional
	public List<Monument> getMostVisitedMonuments() {
		return monumentDao.getMostVisitedMonuments();
	}

}
