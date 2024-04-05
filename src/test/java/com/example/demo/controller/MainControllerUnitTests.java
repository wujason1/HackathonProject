package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.config.JwtService;
import com.example.demo.service.UserService;

@ExtendWith(MockitoExtension.class)
public class MainControllerUnitTests {
	
	@InjectMocks
    private MainController mainController;

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;
    
    @Test
    public void testDefaultEndpoint() {
        ResponseEntity<Object> response = mainController.defaultEndpoint();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("{\"message\": \"Welcome to the default endpoint!\"}");

    }
    
    @Test
    public void testRegisterUserAccount_WithValidToken() throws Exception {
        String validToken = "validToken";
        String userEmail = "user@example.com";

        when(jwtService.extractUsername(validToken)).thenReturn(userEmail);
        when(userDetailsService.loadUserByUsername(userEmail)).thenReturn(null);
        when(jwtService.isTokenValid(validToken, null)).thenReturn(true);

        ResponseEntity<Object> response = mainController.registerUserAccount(null, null, null, "Bearer " + validToken);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("{\"message\": \"valid token, logged in\"}");
    }
}
