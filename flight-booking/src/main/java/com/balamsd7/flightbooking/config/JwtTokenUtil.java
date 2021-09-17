package com.balamsd7.flightbooking.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = 1L;

    @Value("${jwt.secret}")
    private String jwtSecret;

    // few things needed
    // username from token
    // expiration date/time from token

    public String getUsernameFromToken(String token){
        return getClaimsFromToken(token,Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimsFromToken(token,Claims::getExpiration);
    }

    public <T> T getClaimsFromToken(String token, Function<Claims,T> claimsTFunction){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsTFunction.apply(claims);
    }
    // to parse the token, we will need the same secret that was used to generate the token
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    // generate token from spring security user details
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return generateJwt(claims,userDetails.getUsername());
    }

    public String generateJwt(Map<String,Object> claims,String subject){
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 2000000)) // fix this
                .signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
    }

    public Boolean validateToken(String token,UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isValidToken(token));
    }

    // verify in case token is valid or not
    private Boolean isValidToken(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
