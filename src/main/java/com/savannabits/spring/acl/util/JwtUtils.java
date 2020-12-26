package com.savannabits.spring.acl.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;
@Component
public class JwtUtils {
    public String generateJWToken(UserDetails userDetails) {
        String secretKey = "mysecret";
        String token = Jwts.builder()
                .setId(userDetails.getUsername())
                .setSubject(userDetails.getUsername())
                .claim("authorities",
                        userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000*60)))
                .signWith(SignatureAlgorithm.HS512,secretKey.getBytes())
                .compact()
                ;
        return token;
    }
}
