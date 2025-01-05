package com.formation.projet.security;
import com.formation.projet.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
public class MockitoJwtTokenFilterTest {


        private JwtTokenFilter jwtTokenFilter;

        @Mock
        private JwtTokenUtil jwtTokenUtil;

        @Mock
        private UserDetailsServiceImpl userDetailsService;

        @Mock
        private HttpServletRequest mockRequest;

        @Mock
        private HttpServletResponse mockResponse;

        @Mock
        private FilterChain mockFilterChain;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
            jwtTokenFilter = new JwtTokenFilter();
            jwtTokenFilter.jwtTokenUtil = jwtTokenUtil;
            jwtTokenFilter.userDetailsService = userDetailsService;
        }

        @Test
        void testValidToken() throws Exception {
            String token = "Bearer valid.token.here";
            String username = "testUser";
            UserDetails userDetails = User.withUsername(username).password("password").roles("USER").build();

            when(mockRequest.getHeader("Authorization")).thenReturn(token);
            when(jwtTokenUtil.validateJwtToken("valid.token.here")).thenReturn(true);
            when(jwtTokenUtil.getUserNameFromJwtToken("valid.token.here")).thenReturn(username);
            when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

            jwtTokenFilter.doFilterInternal(mockRequest, mockResponse, mockFilterChain);

            verify(jwtTokenUtil, times(1)).validateJwtToken("valid.token.here");
            verify(mockFilterChain, times(1)).doFilter(mockRequest, mockResponse);
        }

        @Test
        void testInvalidToken() throws Exception {
            String token = "Bearer invalid.token";

            when(mockRequest.getHeader("Authorization")).thenReturn(token);
            when(jwtTokenUtil.validateJwtToken("invalid.token")).thenReturn(false);

            jwtTokenFilter.doFilterInternal(mockRequest, mockResponse, mockFilterChain);

            verify(jwtTokenUtil, times(1)).validateJwtToken("invalid.token");
            verify(mockFilterChain, times(1)).doFilter(mockRequest, mockResponse);
        }

        @Test
        void testNoToken() throws Exception {
            when(mockRequest.getHeader("Authorization")).thenReturn(null);

            jwtTokenFilter.doFilterInternal(mockRequest, mockResponse, mockFilterChain);

            verify(jwtTokenUtil, never()).validateJwtToken(anyString());
            verify(mockFilterChain, times(1)).doFilter(mockRequest, mockResponse);
        }

    @Test
    void testExceptionWhileProcessing() throws Exception {
        String token = "Bearer valid.token.here";

        // Mock the request and token behavior
        when(mockRequest.getHeader("Authorization")).thenReturn(token);
        when(jwtTokenUtil.validateJwtToken("valid.token.here")).thenThrow(new RuntimeException("Mock Exception"));

        // Assert that the exception is thrown
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            jwtTokenFilter.doFilterInternal(mockRequest, mockResponse, mockFilterChain);
        });

        // Verify the exception message
        assertEquals("Cannot set user authenticationMock Exception", exception.getMessage());

        // Verify that the filter chain was NOT invoked
        verify(mockFilterChain, never()).doFilter(mockRequest, mockResponse);
    }

}
