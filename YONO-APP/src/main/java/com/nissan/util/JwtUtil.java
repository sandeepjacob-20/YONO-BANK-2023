package com.nissan.util;

import java.nio.file.AccessDeniedException;
import java.sql.Date;

import org.springframework.stereotype.Component;

import com.nissan.model.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	// secret key for TOKEN
	private static String secretAdmin = "THIS_IS_ADMIN";
	private static String secretCustomer = "THIS_IS_CUSTOMER";

	// expiration time
	private static long expiryDurationn = 60 * 60;

	// generate token for admin: header.payload.signature
	public String generateJwtAdmin(Users user) {
		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiryDurationn * 1000;

		// set issuedTime and ExpiryTime in token
		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);

		// claims
		Claims claim = Jwts.claims().setIssuer(user.getUserId().toString()).setIssuedAt(issuedAt)
				.setExpiration(expiryAt);
		// generate JWT token using claims
		return Jwts.builder().setClaims(claim).signWith(SignatureAlgorithm.HS512, secretAdmin).compact();
	}

	// generate token for Customer: header.payload.signature
	public String generateJwtCustomer(Users user) {
		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiryDurationn * 1000;

		// set issuedTime and ExpiryTime in token
		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);

		// claims
		Claims claim = Jwts.claims().setIssuer(user.getUserId().toString()).setIssuedAt(issuedAt)
				.setExpiration(expiryAt);
		// generate JWT token using claims
		return Jwts.builder().setClaims(claim).signWith(SignatureAlgorithm.HS512, secretCustomer).compact();
	}

	// checking token is valid or not for admin
	public Claims verifyAdmin(String authorization) throws AccessDeniedException {
		try {
			Claims claim = Jwts.parser().setSigningKey(secretAdmin).parseClaimsJws(authorization).getBody();
			return claim;
		} catch (Exception e) {
			throw new AccessDeniedException("Sorry! ACCESS DENIED!!");
		}
	}
	
	// checking token is valid or not for customer
		public Claims verifyCustomer(String authorization) throws AccessDeniedException {
			try {
				Claims claim = Jwts.parser().setSigningKey(secretCustomer).parseClaimsJws(authorization).getBody();
				return claim;
			} catch (Exception e) {
				throw new AccessDeniedException("Sorry! ACCESS DENIED!!");
			}
		}
}