package com.services;

import java.util.List;

import com.entities.Monument;

public interface IMonumentService {

	public Monument getMonument(Long id);

	public List<Monument> getAllMonument();
	
	public List<Monument> getMostVisitedMonuments();

}
