package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(nullable = false, unique = true, name = "username")
	private String username;

	@NotEmpty
	@Column(nullable = false, unique = true, name = "email")
	private String email;

	@NotEmpty
	@Column(nullable = false, name = "password")
	private String password;

	@Column(name = "date_created")
	private Date dateCreated;

	@Column(name = "enabled")
	private boolean enabled = false;

	@ManyToMany(fetch = FetchType.EAGER, cascade = {
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH
	})
	@JoinTable(name = "user_authority", joinColumns = { @JoinColumn(name = "user_id") }, 
					inverseJoinColumns = { @JoinColumn(name = "authority_id") })
	private Set<Authority> authorities;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Set<Circuit> circuits;

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public void addAuthoriry(Authority authority) {
		if (authorities == null) {
			authorities = new HashSet<Authority>();
		}
		authorities.add(authority);
	}

	public void addCircuit(Circuit circuit) {
		if (circuits == null) {
			circuits = new HashSet<Circuit>();
		}
		circuits.add(circuit);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public Set<Circuit> getCircuits() {
		return circuits;
	}

	public void setCircuits(Set<Circuit> circuits) {
		this.circuits = circuits;
	}
}
