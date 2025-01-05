package com.formation.projet.controllerTest;
import com.formation.projet.controller.AuthController;
import com.formation.projet.entities.*;
import com.formation.projet.repository.RoleRepository;
import com.formation.projet.repository.UserRepository;
import com.formation.projet.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import static org.mockito.Mockito.*;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MockitoAuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }
    @Test
    void testUserLogin() throws Exception {
        // Mock CustomUserBean
        CustomUserBean userBean = mock(CustomUserBean.class);
        when(userBean.getUsername()).thenReturn("user");

        // Use a wildcard collection type for the mock
        Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        when(userBean.getAuthorities()).thenAnswer(invocation -> authorities);

        // Mock Authentication
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userBean);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        // Mock JwtTokenUtil
        when(jwtTokenUtil.generateJwtToken(authentication)).thenReturn("mocked-jwt-token");

        // Perform MockMvc Test
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\": \"user\", \"password\": \"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mocked-jwt-token"))
                .andExpect(jsonPath("$.roles[0]").value("ROLE_USER"));
    }


    @Test
    void testUserSignup_Success() throws Exception {
        // Mock RoleRepository
        Role roleUser = new Role();
        roleUser.setId(1);
        roleUser.setRoleName(Roles.ROLE_USER);
        when(roleRepository.findByRoleName(Roles.ROLE_USER)).thenReturn(Optional.of(roleUser));

        // Mock UserRepository
        when(userRepository.existsByUserName("newuser")).thenReturn(false);
        when(userRepository.existsByEmail("newuser@example.com")).thenReturn(false);

        // Mock PasswordEncoder
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        // Perform the MockMvc request
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "userName": "newuser",
                                    "email": "newuser@example.com",
                                    "password": "password",
                                    "roles": ["user"]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().string("User signed up successfully"));

        // Verify interactions
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUserSignup_Failure_ExistingUsername() throws Exception {
        // Mock UserRepository
        when(userRepository.existsByUserName("existinguser")).thenReturn(true);

        // Perform the MockMvc request
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "userName": "existinguser",
                                    "email": "user@example.com",
                                    "password": "password",
                                    "roles": ["user"]
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username is already taken"));

        // Verify no save operation is performed
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testUserSignup_Failure_ExistingEmail() throws Exception {
        // Mock UserRepository
        when(userRepository.existsByEmail("existingemail@example.com")).thenReturn(true);

        // Perform the MockMvc request
        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "userName": "newuser",
                                    "email": "existingemail@example.com",
                                    "password": "password",
                                    "roles": ["user"]
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email is already taken"));

        // Verify no save operation is performed
        verify(userRepository, never()).save(any(User.class));
    }
}
