package com.eventos.eventos.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.eventos.eventos.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${API.SECURITY.SECRET}")
    private String secret;
    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create().withIssuer("login-auth-api").withSubject(user.getEmail()).withClaim("name", user.getName()).withExpiresAt(this.generateExpirationDate()).sign(algorithm);
            return token;

        }catch (JWTCreationException exception){
            throw new RuntimeException("ERROR ENQUANTO CRIA TOKEN");
        }

    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("login-auth-api").build().verify(token).getSubject();
        }catch (JWTVerificationException exception){
            return null;
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of(String.valueOf(-3)));
    }
}
