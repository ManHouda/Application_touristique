package com.configuration.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.java.utils.UserDetailsImp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

@Component
public final class JwtTokenUtil {
	
	//public static final int EXPIRATION_IN_SECONDS = 120000;

	//private static final String JWT_SECRET = "Some$ecretKey";

	private Clock clock = DefaultClock.INSTANCE;

	//@Value("${jwt.secret}")
	private static final String SECRET = "jwtAppSecretKey";

	//@Value("${jwt.expiration}")
	private static final Long EXPIRATION = Long.valueOf(86400);

	private JwtTokenUtil() {
		// Hide default constructor
	}

	public String getUsernameFromToken(String token) {
		return extractClaims(token).getSubject();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		UserDetailsImp user = (UserDetailsImp) userDetails;
		final String username = getUsernameFromToken(token);
		return (username.equals(user.getUsername()) && !isTokenExpired(token));
	}

	public Date getIssuedAtDateFromToken(String token) {
		return extractClaims(token).getIssuedAt();
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		final Date createdDate = clock.now();
		final Date expirationDate = calculateExpirationDate(createdDate);

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate)
				.setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	private Date calculateExpirationDate(Date createdDate) {
		return new Date(createdDate.getTime() + EXPIRATION * 1000);
	}

	public static String createToken(String username, Date issueDate) {
		String jwtToken = Jwts.builder().setSubject(username).setIssuedAt(issueDate)
				.setExpiration(new Date(issueDate.getTime() + (EXPIRATION * 1000)))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();

		return jwtToken;
	}

	public static String getSubject(String token) {
		Claims claims = extractClaims(token);
		return claims.getSubject();
	}

	public static String refreshToken(String token, long expirationInSeconds) {
		final Claims claims = extractClaims(token);

		Date now = new Date();
		claims.setIssuedAt(now);
		claims.setExpiration(new Date(now.getTime() + (EXPIRATION*1000)));

		return createTokenFromClaims(claims);
	}

	public static boolean isTokenExpired(String token) {
		final Claims claims = extractClaims(token);
		Date now = new Date();

		return now.after(claims.getExpiration());
	}

	private static String createTokenFromClaims(Claims claims) {
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	private static Claims extractClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	}

}