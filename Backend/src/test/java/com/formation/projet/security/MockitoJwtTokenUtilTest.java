package com.formation.projet.security;
import com.formation.projet.entities.CustomUserBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

public class MockitoJwtTokenUtilTest {
    private JwtTokenUtil jwtTokenUtil;

    @BeforeEach
    void setUp() {
        jwtTokenUtil = new JwtTokenUtil();
        // Inject mock configuration values
        ReflectionTestUtils.setField(jwtTokenUtil, "jwtTokenSecret", "mysecretkey");
        ReflectionTestUtils.setField(jwtTokenUtil, "jwtTokenExpiration", 500000L);
    }

    @Test
    void testGenerateJwtToken() {
        // Mock Authentication and CustomUserBean
        Authentication authentication = mock(Authentication.class);
        CustomUserBean userBean = mock(CustomUserBean.class);
        when(authentication.getPrincipal()).thenReturn(userBean);
        when(userBean.getUsername()).thenReturn("testUser");

        // Generate token
        String token = jwtTokenUtil.generateJwtToken(authentication);

        assertNotNull(token);
        String username = jwtTokenUtil.getUserNameFromJwtToken(token);
        assertEquals("testUser", username);
    }

    @Test
    void testValidateExpiredJwtToken() throws InterruptedException {
        // Mock Authentication and CustomUserBean
        Authentication authentication = mock(Authentication.class);
        CustomUserBean userBean = mock(CustomUserBean.class);
        when(authentication.getPrincipal()).thenReturn(userBean);
        when(userBean.getUsername()).thenReturn("testUser");

        // Short expiration time for testing
        ReflectionTestUtils.setField(jwtTokenUtil, "jwtTokenExpiration", 1000L);

        String token = jwtTokenUtil.generateJwtToken(authentication);

        // Wait for the token to expire
        Thread.sleep(2000);

        boolean isValid = jwtTokenUtil.validateJwtToken(token);
        assertFalse(isValid, "Expired token should not be valid");
    }
}
