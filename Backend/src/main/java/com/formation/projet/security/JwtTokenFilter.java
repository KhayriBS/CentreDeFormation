package com.formation.projet.security;

import java.io.IOException;

import com.formation.projet.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // Ignorer certains endpoints publics comme "/auth" et "/h2-console"
            String requestURI = request.getRequestURI();
            if (requestURI.startsWith("/h2-console") || requestURI.startsWith("/auth")) {
                filterChain.doFilter(request, response);
                return; // Ignorer le traitement JWT pour ces endpoints
            }

            // Extraire le token de la requête
            String token = getTokenFromRequest(request);
            if (token != null && jwtTokenUtil.validateJwtToken(token)) {
                String username = jwtTokenUtil.getUserNameFromJwtToken(token);

                // Charger les détails de l'utilisateur
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (userDetails != null) {
                    // Créer une authentification et la définir dans le contexte de sécurité
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Définir l'authentification dans le contexte de sécurité
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.debug("User authenticated: {}"+ username);
                } else {
                    logger.warn("User not found: {}"+ username);
                }
            } else {
                logger.warn("JWT Token is invalid or missing");
            }
        } catch (Exception e) {
            // Loggez l'erreur au lieu de lancer une exception
            logger.error("Error during JWT authentication: " + e.getMessage(), e);
        }

        // Continuer avec le filtre suivant
        filterChain.doFilter(request, response);
    }

    /**
     * Extrait le token JWT de l'en-tête Authorization.
     *
     * @param request La requête HTTP.
     * @return Le token JWT ou null s'il est absent.
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7); // Supprime "Bearer " pour obtenir le token brut
        }
        return null;
    }
}
