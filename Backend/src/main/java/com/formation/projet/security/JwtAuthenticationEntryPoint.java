package com.formation.projet.security;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // Extraction du token de l'en-tête Authorization
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            logger.error("Unauthorized access: No Authorization token provided");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Access: No token provided");
        } else {
            // Log de l'exception avec le message d'erreur
            logger.error("Unauthorized access error: {}", authException.getMessage());
            // Optionnellement, loguer une partie du token pour déboguer, sans exposer toute l'information
            logger.debug("Token provided: {}", token.substring(0, Math.min(token.length(), 20)) + "...");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Access: Invalid or expired token");
        }
    }
}
