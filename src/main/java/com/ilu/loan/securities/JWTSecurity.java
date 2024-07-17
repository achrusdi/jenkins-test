package com.ilu.loan.securities;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ilu.loan.entities.Role;
import com.ilu.loan.entities.User;

@Component
public class JWTSecurity {
    private String secretKey = "secret";
    private String issuer = "Livecode Loan Springboot";
    private int validityInSeconds = 3600;
    private String audience = "Livecode Loan Springboot";

    private Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes(StandardCharsets.UTF_8));

    
    public String generateToken(User user) {
        List<String> roles = user.getRoles().stream().map(role -> role.getRole().name()).toList(); 
        return JWT.create()
                .withSubject(user.getUser_id())
                .withAudience(audience)
                .withIssuer(issuer)
                .withExpiresAt(Instant.now().plusSeconds(validityInSeconds))
                .withIssuedAt(Instant.now())
                .withClaim("roles", roles)
                .sign(algorithm);
    }

    public Map<String, String> validateTokenAndGetData(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);

            Map<String, String> data = new HashMap<>();
            data.put("userId", decodedJWT.getSubject());
            data.put("roles", decodedJWT.getClaim("roles").asString());

            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String getUserIdFromJwtToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getSubject();
        } catch (JWTDecodeException e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
}