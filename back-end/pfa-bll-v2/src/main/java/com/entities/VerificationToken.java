package com.entities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "verification_token")
public class VerificationToken {

	private static final int EXPIRATION = 60 * 24;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name="expiration_date")
	private Date expirationDate;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {
						CascadeType.DETACH,
						CascadeType.MERGE,
						CascadeType.PERSIST,
						CascadeType.REFRESH })
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public VerificationToken() {
	}

	public VerificationToken(User user, String token) {
		this.token = token;
		this.user = user;
		this.createdDate = new Date();
		this.expirationDate = calculateExpirationDate(EXPIRATION);
	}

	private Date calculateExpirationDate(int expirationTimeInMinutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Timestamp(calendar.getTime().getTime()));
		calendar.add(Calendar.MINUTE, expirationTimeInMinutes);
		return new Date(calendar.getTime().getTime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
