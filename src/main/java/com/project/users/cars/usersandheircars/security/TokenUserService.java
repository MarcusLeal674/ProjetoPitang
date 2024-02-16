package com.project.users.cars.usersandheircars.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project.users.cars.usersandheircars.entities.Users;

@Service
public class TokenUserService {
	
	@Value("api.security.user.token.secret")
	private String secritToken;
	
	public String generateUserToken(Users users) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secritToken);
			String userToken = JWT.create()
					.withIssuer("api-token")
					.withSubject(users.getLogin())
					.withExpiresAt(this.generatExpirationDate())
					.sign(algorithm);
			return userToken;
		} catch (JWTCreationException j) {
			throw new RuntimeException("Error in Generating User Token: ", j);
		}
	}
	
	public String validateUserToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secritToken);
			return JWT.require(algorithm)
					.withIssuer("api-token")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException j) {
			return "";
		}
	}
	
	private Instant generatExpirationDate() {
		 return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
	}

}
