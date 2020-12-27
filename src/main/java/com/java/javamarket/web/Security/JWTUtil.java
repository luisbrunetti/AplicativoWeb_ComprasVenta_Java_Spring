package com.java.javamarket.web.Security;
/*
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;


@Component
public class JWTUtil {
    private static final String KEY = "oracle";
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() * 1000* 60 *60 *10))
                .signWith(SignatureAlgorithm.HS256,KEY).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        //1.- Creado para el usuario
        //2.- no haya experiado

        //Primero se valida que el usuario que viene en la peteción en el mismo que se muestra en el tooken
        return userDetails.getUsername().
                equals(extractUsername(token))
                && !isTokenExperired(token);
    }
    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }
    public boolean isTokenExperired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }
    //Claims == Clase en JWT
    private Claims getClaims (String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
*/