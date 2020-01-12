package com.services.imp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ICircuitDao;
import com.entities.Circuit;
import com.services.ICircuitService;

@Service
public class CircuitServiceImp implements ICircuitService {
	
	@Autowired
	private ICircuitDao circuitDao;
	
	@Transactional
	public void saveCircuit(Circuit circuit) {
		circuitDao.save(circuit);
	}

	@Transactional
	public Circuit getCircuit(Long id) {
		return circuitDao.find(id);
	}

	@Transactional
	public void updateCircuit(Circuit c) {
		circuitDao.update(c);
	}

	@Transactional
	public void deleteCircuit(Long id) {
		circuitDao.delete(id);
	}
}
