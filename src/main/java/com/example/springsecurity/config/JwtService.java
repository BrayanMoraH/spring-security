package com.example.springsecurity.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";

    public String extractUsername(String token){
        return extractClaimToken(token, Claims::getSubject);
    }

    private <T> T extractClaimToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                //.setSigningKey(getSignKey()) //TODO: UPDATE THIS LATER
                .build().
                parseSignedClaims(token)
                .getPayload();
    }

    private String createToken(Map<String, Object> claims, String username){
        return Jwts.builder()
                .issuer(username)
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(getSignKey())
                .compact();
    }


    private SecretKey getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public Date extractExpiration(String token){
        return extractClaimToken(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities){
        Map<String, Object> claims = new HashMap<>();
        authorities.forEach(claim -> {
            claims.put("role", claim.getAuthority());
        });
        return createToken(claims, username);
    }

}
