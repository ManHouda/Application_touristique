package com.services;

import java.util.List;

import com.entities.Monument;
import com.entities.Region;

public interface IRegionService {
	
	public List<Region> getAllRegions();
	
	public List<Monument> getMonumentsByRegion(Long id);
	
}
