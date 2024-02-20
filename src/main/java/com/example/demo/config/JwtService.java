package com.example.demo.config;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final static SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String extractUsername(String token) {
        // TODO Auto-generated method stub
        Claims claimsBody = extractAllClaims(token);
        String username = claimsBody.getSubject();
        System.out.println("username: " + username);
        return username;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }


    private Claims extractAllClaims(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getSigInKey()).build().parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return body;
    }

    private SecretKey getSigInKey() {
        // TODO Auto-generated method stub
        byte[] keyBytes = secretKey.getEncoded();
        String base64Key = Base64.getEncoder().encodeToString(keyBytes);
        SecretKey decodedSecretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Key));
        return decodedSecretKey;
    }

    public String generateToken(UserDetails userDetails) {
        System.out.println("userDetails: " + userDetails.getUsername());
        String token = Jwts.builder()
                .subject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(getSigInKey(), SignatureAlgorithm.HS256)
                .compact();
        System.out.println("token: " + token);
        return token;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        Function<Claims, Date> getExpiration = new Function<Claims, Date>() {
            @Override
            public Date apply(Claims claims) {
                return claims.getExpiration();
            }
        };
        Date expirationDate = extractClaim(token, getExpiration);
        boolean extractExpiration = expirationDate.before(new Date());
        if(username.equals(userDetails.getUsername()) && !extractExpiration) {
            return true;
        }
        return false;
    }



}
