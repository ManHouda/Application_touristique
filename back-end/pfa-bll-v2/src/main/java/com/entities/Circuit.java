package com.entities;

import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "circuit")
public class Circuit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "start_monument_id")
	private Monument start;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "end_monument_id")
	private Monument end;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { 
					CascadeType.DETACH, 
					CascadeType.MERGE, 
					CascadeType.PERSIST,
					CascadeType.REFRESH })
	@JoinTable(name = "circuit_monument", 
					joinColumns = @JoinColumn(name = "circuit_id"), 
					inverseJoinColumns = @JoinColumn(name = "monument_id"))
	private List<Monument> monuments;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "circuit_id")
	private List<Comment> comments;

	public Circuit() {
	}

	public Circuit(Monument start, Monument end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Monument getStart() {
		return start;
	}

	public void setStart(Monument start) {
		this.start = start;
	}

	public Monument getEnd() {
		return end;
	}

	public void setEnd(Monument end) {
		this.end = end;
	}

	public List<Monument> getMonuments() {
		return monuments;
	}

	public void setMonuments(List<Monument> monuments) {
		this.monuments = monuments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
