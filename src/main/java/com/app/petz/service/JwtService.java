package com.app.petz.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "g/yu7/LkjqUDOfE2TjdX4nh2WfOMEuAaNETIcTDOXaGmizl/8AD6qr8gNJIboevetNxaWnQR6pAqeLLt+AjwTnWazJRDZPuge39cjJIPRigevAcsTOfAxFLTA45pYqyzUaBckvr69WaNX+7sG5HLUL9OwUM0MNSo8PFkxwDqJey9wA3hC3cA/9Q9J39lwQkY+9qiEKFClBMtFcRjen+dW1flf+q9WGsSCiLxZDEqKBGf/WVR59zUI/JLTrnRXzfpt2H9p3IOdSD3SPWyJ6wW6DWo5KYdbE/R1hPzbblp7fdRT1RxCd25AKEVboT9QjHLI1qHDtUO+Pd68LjtkxT+AgFWAwbw2/xQdwqRnoZsu2Y=";
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //método alternativo para geração de token sem a necessidade de passar as claims extras do jwt
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24)) //24h * 1000ms
                .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
