package com.services.imp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ITouristDao;
import com.entities.Tourist;
import com.services.ITouristService;

@Service
public class TouristServiceImp implements ITouristService {
	
	@Autowired
	ITouristDao touristDao;
	
	@Transactional
	public void saveCustomer(Tourist tourist) {
		
		System.out.println("from the service!");
		
		touristDao.save(tourist);
	}
}
