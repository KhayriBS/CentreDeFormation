package com.formation.projet.security;

import java.util.Date;

import com.formation.projet.entities.CustomUserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Component
public class JwtTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${jwttoken.secret}")
    private String jwtTokenSecret;

    @Value("${jwttoken.expiration}")
    private long jwtTokenExpiration;

    // Génère un JWT token pour l'utilisateur authentifié
    public String generateJwtToken(Authentication authentication) {
        CustomUserBean userPrincipal = (CustomUserBean) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtTokenExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtTokenSecret)
                .compact();
    }

    // Valide un token JWT
    public boolean validateJwtToken(String token) {
        try {
            // Utiliser l'ancienne méthode parser()
            Jwts.parser()
                    .setSigningKey(jwtTokenSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (UnsupportedJwtException exp) {
            logger.error("Invalid JWT token: claimsJws argument does not represent Claims JWS - {}", exp.getMessage());
        } catch (MalformedJwtException exp) {
            logger.error("Invalid JWT token: claimsJws string is not a valid JWS - {}", exp.getMessage());
        } catch (SignatureException exp) {
            logger.error("Invalid JWT token: JWS signature validation failed - {}", exp.getMessage());
        } catch (ExpiredJwtException exp) {
            logger.error("Expired JWT token: Claims has an expiration time before the method is invoked - {}", exp.getMessage());
        } catch (IllegalArgumentException exp) {
            logger.error("Invalid JWT token: claimsJws string is null or empty - {}", exp.getMessage());
        }
        return false;
    }}

// Récupère le nom de l'utilisateur à partir du JWT tok
