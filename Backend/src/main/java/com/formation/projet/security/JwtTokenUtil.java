package com.formation.projet.security;

import com.formation.projet.entities.CustomUserBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwttoken.secret}")
    private String jwtTokenSecret;

    @Value("${jwttoken.expiration}")
    private long jwtTokenExpiration;

    public String generateJwtToken(Authentication authentication) {
        CustomUserBean userPrincipal = (CustomUserBean) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtTokenExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtTokenSecret)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtTokenSecret)
                    .parseClaimsJws(token);  // Utilisation de parser() au lieu de parserBuilder()
            return true;
        } catch (UnsupportedJwtException exp) {
            System.out.println("Unsupported JWT: " + exp.getMessage());
        } catch (MalformedJwtException exp) {
            System.out.println("Malformed JWT: " + exp.getMessage());
        } catch (SignatureException exp) {
            System.out.println("Invalid JWT signature: " + exp.getMessage());
        } catch (ExpiredJwtException exp) {
            System.out.println("JWT expired: " + exp.getMessage());
        } catch (IllegalArgumentException exp) {
            System.out.println("JWT string is empty or null: " + exp.getMessage());
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtTokenSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
