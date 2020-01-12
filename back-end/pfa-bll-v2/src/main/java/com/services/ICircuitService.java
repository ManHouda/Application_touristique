package com.services;

import com.entities.Circuit;

public interface ICircuitService {
	
	public void saveCircuit(Circuit circuit);
	
	public Circuit getCircuit(Long id);
	
	public void updateCircuit(Circuit c);
	
	public void deleteCircuit(Long id);
}
