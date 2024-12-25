package com.formation.projet.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // Configuration CORS globale pour l'application
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permet toutes les requêtes CORS depuis le frontend Angular (remplacer par l'URL appropriée)
        registry.addMapping("/**")
                .allowedOrigins("http://192.168.50.4:4202")  // Adresse de votre frontend Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Méthodes HTTP autorisées
                .allowedHeaders("*")  // Autoriser tous les en-têtes
                .allowCredentials(true)  // Permet l'envoi des cookies (si nécessaire)
                .maxAge(3600);  // Durée maximale pour la pré-demande CORS en secondes
    }
}
