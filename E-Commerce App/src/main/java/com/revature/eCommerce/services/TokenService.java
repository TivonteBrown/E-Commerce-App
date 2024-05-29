package com.revature.eCommerce.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import com.revature.eCommerce.dto.responses.Principal;
import com.revature.eCommerce.models.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SuppressWarnings("deprecation")
public class TokenService {
    private String secretKey;

    public TokenService() throws IOException{
        InputStream is = getClass().getClassLoader().getResourceAsStream("application.properties");
        Properties props = new Properties();
        props.load(is);
        secretKey = props.getProperty("secret");
    }

    public String generateToken(Principal principal){
        return Jwts.builder().setId(principal.getUserID())
        .setIssuer("ecommerce")
        .setSubject(principal.getName())
        .claim("roleID", principal.getRole().getID())
        .claim("roleName", principal.getRole().getRoleName())
        .setExpiration(new Date(System.currentTimeMillis() + 3600000))
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();

    }

    public Principal parseToken(String token){
        Claims claims = Jwts.parser()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token)
        .getBody();

        return new Principal(
            claims.getId(),
            claims.getSubject(),
            new Role(
                claims.get("roleID", String.class),
                claims.get("roleName", String.class)));
    }

}
