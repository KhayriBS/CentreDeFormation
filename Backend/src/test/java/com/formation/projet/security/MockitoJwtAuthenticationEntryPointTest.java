package com.formation.projet.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class MockitoJwtAuthenticationEntryPointTest {
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Mock
    private HttpServletRequest mockRequest;

    @Mock
    private HttpServletResponse mockResponse;

    @Mock
    private AuthenticationException mockAuthException;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
    }

    @Test
    void testCommence() throws IOException, ServletException {
        // Arrange
        String errorMessage = "Test unauthorized error";
        when(mockAuthException.getMessage()).thenReturn(errorMessage);

        // Act
        jwtAuthenticationEntryPoint.commence(mockRequest, mockResponse, mockAuthException);

        // Assert
        verify(mockResponse, times(1)).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Access");
    }
}
