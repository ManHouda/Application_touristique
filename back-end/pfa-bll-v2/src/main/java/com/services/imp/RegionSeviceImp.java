package com.services.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.IRegionDao;
import com.entities.Monument;
import com.entities.Region;
import com.services.IRegionService;

@Service
public class RegionSeviceImp implements IRegionService {
	
	@Autowired
	private IRegionDao regionDao;
	
	@Transactional
	public List<Region> getAllRegions() {
		return regionDao.getAll();
	}
	
	@Transactional
	public List<Monument> getMonumentsByRegion(Long id) {
		
		Region region = regionDao.find(id);
		
		if(region == null)
			return null;
		
		return region.getMonuments();
		
	}

}
