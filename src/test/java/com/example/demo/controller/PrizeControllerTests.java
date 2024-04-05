package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.PrizeService;

@WebMvcTest
@ContextConfiguration(classes = PrizeController.class) 
@AutoConfigureMockMvc(addFilters=false)
public class PrizeControllerTests {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrizeService prizeService;
    
    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("user@example.com");
    }

    @Test
    public void testGetFirstPrize_CanClaimPrize_True() throws Exception {
        // Mocking prizeService to return true
        when(prizeService.canClaimPrize("user@example.com", 1L)).thenReturn(true);

        // Perform GET request
        mockMvc.perform(get("/prizes/1"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
               .andExpect(jsonPath("$.message").value(true));
    }

    @Test
    public void testGetFirstPrize_CanClaimPrize_False() throws Exception {
        // Mocking prizeService to return false
        when(prizeService.canClaimPrize("user@example.com", 1L)).thenReturn(false);

        // Perform GET request
        mockMvc.perform(get("/prizes/1"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
               .andExpect(jsonPath("$.message").value(false));
    }

}
