package com.pharmacy.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.pharmacy.service.MyUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {
	
	@Value("${pharmacy.app.jwtSecret}")
	private String jwtSecret;

	@Value("${pharmacy.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	public String generateJwtToken(Authentication authentication) {

		MyUserDetails userPrincipal = (MyUserDetails) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.claim("username",userPrincipal.getUsername())
				.claim("role", userPrincipal.getRole())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			System.out.print("Invalid JWT signature: {}"+ e.getMessage());
		} catch (MalformedJwtException e) {
			System.out.print("Invalid JWT token: {}"+ e.getMessage());
		} catch (ExpiredJwtException e) {
			System.out.print("JWT token is expired: {}"+ e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.out.print("JWT token is unsupported: {}"+e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.print("JWT claims string is empty: {}"+ e.getMessage());
		}

		return false;
	}
}
