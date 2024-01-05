package com.abhijeet.noteapp.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {

    private static final String SECRET_KEY = "AbhiSpeer9910iwquaysdfgvhbj21346543qrewdafsghy6534222456ytgfe";
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000)) //currenlty set token vaid for 1 hour
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

    }

    public Claims getClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        final Claims claims = getClaimsFromToken(token);
        return (claims.getSubject().equals(userDetails.getUsername()) && !isTokenExpired(claims.getExpiration()));
    }

    private boolean isTokenExpired(Date expDate) {
        return expDate.before(new Date());
    }
}
