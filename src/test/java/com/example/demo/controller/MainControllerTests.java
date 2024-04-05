package com.example.demo.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.config.JwtService;
import com.example.demo.service.UserService;

@WebMvcTest
@ContextConfiguration(classes = MainController.class)
@AutoConfigureMockMvc(addFilters = false)
public class MainControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private JwtService jwtService;

	@MockBean
	UserDetailsService userDetailsService;

	@Test
	public void testDefaultEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk())
				// .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Welcome to the default endpoint!"));
	}
	
	@Test
    public void testRegisterUserAccount_WithValidCredentials() throws Throwable {
		String email = "user@example.com";
        String password = "password";
        String token = "Bearer token123";
        
        when(jwtService.extractUsername(token.substring(7))).thenReturn(email);
        when(userDetailsService.loadUserByUsername(email)).thenReturn(null);
        when(jwtService.isTokenValid(token.substring(7), null)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                //.contentType(MediaType.APPLICATION_JSON)
                .param("email", email)
                .param("password", password)
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("valid token, logged in"));
    
    }

}
