package com.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entities.Monument;
import com.services.IMonumentService;
import com.services.IRegionService;

@RestController
@RequestMapping("/api/monuments")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class MonumentController {

	@Autowired
	private IRegionService regionService;

	@Autowired
	private IMonumentService monumentService;

	@GetMapping("/monuments")
	public ResponseEntity<List<Monument>> getAllMonuments() {

		List<Monument> monuments = monumentService.getAllMonument();

		return new ResponseEntity<>(monuments, HttpStatus.OK);

	}

	@GetMapping("/monuments-region")
	public ResponseEntity<List<Monument>> getMonumentsByRegion(@RequestParam("region_id") Long regionId) {

		List<Monument> monuments = regionService.getMonumentsByRegion(regionId);

		return new ResponseEntity<>(monuments, HttpStatus.OK);

	}

	@GetMapping("/monument")
	public ResponseEntity<Monument> getMonumentDetails(@RequestParam("monument_id") Long monumentId) {

		Monument monument = monumentService.getMonument(monumentId);

		return new ResponseEntity<>(monument, HttpStatus.OK);
	}

	@GetMapping("/most-visited")
	public ResponseEntity<List<Monument>> getMostVisitedMonuments() {

		List<Monument> monuments = monumentService.getMostVisitedMonuments();

		return new ResponseEntity<>(monuments, HttpStatus.OK);

	}

}
