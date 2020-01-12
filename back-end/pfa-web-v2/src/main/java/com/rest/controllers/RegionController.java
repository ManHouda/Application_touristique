package com.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entities.Region;
import com.services.IRegionService;

@RestController
@RequestMapping("/api/regions")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class RegionController {

	@Autowired
	private IRegionService regionService;

	@GetMapping("/regions")
	public ResponseEntity<List<Region>> getAllRegions() {

		List<Region> regions = regionService.getAllRegions();

		return new ResponseEntity<>(regions, HttpStatus.OK);

	}

}
