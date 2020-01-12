package com.rest.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entities.Circuit;
import com.entities.Monument;
import com.entities.User;
import com.services.ICircuitService;
import com.services.IUserService;

@RestController
@RequestMapping("/api/circuits")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CircuitController {

	@Autowired
	private ICircuitService circuitService;

	@Autowired
	private IUserService userService;

	@PostMapping("/circuits")
	public ResponseEntity<Circuit> addCircuit(@RequestParam("user_id") Long id, @RequestBody List<Monument> monuments) {

		Circuit circuit = new Circuit(monuments.get(0), monuments.get(monuments.size() - 1));

		circuit.setMonuments(monuments);

		User user = userService.getUser(id);

		if (user != null) {

			Iterator<Monument> it = monuments.iterator();

			while (it.hasNext()) {
				Monument m = it.next();
				m.setVisitsRate(m.getVisitsRate() + 1);
			}
			
			circuitService.saveCircuit(circuit);

			user.addCircuit(circuit);

			userService.updateUser(user);
		}

		return new ResponseEntity<>(circuit, HttpStatus.OK);

	}

	@PutMapping("/circuits")
	public ResponseEntity<Circuit> updateCircuit(@RequestBody Circuit circuit) {

		Circuit c = circuitService.getCircuit(circuit.getId());

		if (c != null) {
			circuitService.updateCircuit(circuit);
		}

		return new ResponseEntity<>(circuit, HttpStatus.OK);
	}

	@DeleteMapping("/circuits")
	public ResponseEntity<Circuit> deleteCircuit(@RequestParam("circuit_id") Long id) {

		Circuit c = circuitService.getCircuit(id);

		if (c != null) {
			circuitService.deleteCircuit(id);
		}

		return new ResponseEntity<>(c, HttpStatus.OK);
	}

}
