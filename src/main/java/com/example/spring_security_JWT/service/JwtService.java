package com.example.spring_security_JWT.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    private String SECRET_KEY = "";

    public JwtService(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");// សំរាប់ generate secret key ដើម្បី sign JWT use algorithm (HmacSha256)
            SecretKey secretKey = keyGenerator.generateKey(); //random secret key
            this.SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.getEncoded()); //encode key to jea Base64
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public String generateToken(String userEmail) {
        return Jwts.builder()
                .setSubject(userEmail) //បញ្ចូល username / email / identifier ជា subject in JWT payload
                .setIssuedAt(new Date(System.currentTimeMillis())) //កំណត់ issue date ពេល token ត្រូវបាន generate
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //កំណត់ expire date rbos token
                .signWith(getGenerateSecretKey()) //sign with secret key
                .compact();
    }

    //get generate key from SECRET_KEY
    private Key getGenerateSecretKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //extract email from JWT token
    public String extractEmail(String token) {
        //parse claims from token
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getGenerateSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); //subject ជា email ដែលបាន set ពេល generate token
    }

    //validate JWT token with userDetails
    public boolean validateToken(String token, UserDetails userDetails) {
        String email = extractEmail(token); //extract email from token
        boolean isNotExpired = Jwts.parserBuilder()
                .setSigningKey(getGenerateSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .after(new Date()); //check token expiration

        //return true only if email matches and token not expired
        return email.equals(userDetails.getUsername()) && isNotExpired;
    }
}
