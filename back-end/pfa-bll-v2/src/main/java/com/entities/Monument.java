package com.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "monument")
public class Monument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "latitude")
	private double latitude;

	@Column(name = "longtitude")
	private double longtitude;

	@Column(name = "name")
	private String name;

	@Column(name = "foundation_date")
	private Date foundationDate;

	@Column(name = "description")
	private String description;

	@Column(name = "visits_rate")
	private int visitsRate;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "circuit_monument", joinColumns = @JoinColumn(name = "monument_id"), inverseJoinColumns = @JoinColumn(name = "circuit_id"))
	private List<Circuit> circuits;

	public Monument() {
	}

	public Monument(double latitude, double longtitude, String name, Date foundationDate, String description) {
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.name = name;
		this.foundationDate = foundationDate;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(Date foundationDate) {
		this.foundationDate = foundationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getVisitsRate() {
		return visitsRate;
	}

	public void setVisitsRate(int visitsRate) {
		this.visitsRate = visitsRate;
	}

	public List<Circuit> getCircuits() {
		return circuits;
	}

	public void setCircuits(List<Circuit> circuits) {
		this.circuits = circuits;
	}
}
