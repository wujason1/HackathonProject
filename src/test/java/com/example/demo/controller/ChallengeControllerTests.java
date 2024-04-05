package com.example.demo.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Challenge;
import com.example.demo.service.ChallengeService;
import com.example.demo.service.PrizeService;

@WebMvcTest
@ContextConfiguration(classes = ChallengeController.class) 
@AutoConfigureMockMvc(addFilters=false)
public class ChallengeControllerTests {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChallengeService challengeService;
    
    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void testResumeSubmission_Success() throws Throwable {
    	MockMultipartFile file = new MockMultipartFile("file", "test123.txt", MediaType.TEXT_PLAIN_VALUE, "Test file content".getBytes());
    	Challenge mockChallenge = mock(Challenge.class);
        when(challengeService.saveResume("resume", file, SecurityContextHolder.getContext().getAuthentication())).thenReturn(mockChallenge);
        
        mockMvc.perform(MockMvcRequestBuilders.multipart("/challenge/resume")
                .file(file))
                .andExpect(status().isOk())
                //.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("File uploaded successfully"));
    
    }
    
    @Test
    public void testLeetcodeSubmission_ValidLink() throws Exception {
        String validLink = "https://leetcode.com/submissions/detail/12345";
        Challenge mockChallenge = mock(Challenge.class);
        when(challengeService.saveLink("leetcode submission link", validLink, SecurityContextHolder.getContext().getAuthentication())).thenReturn(mockChallenge); 
        
        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/challenge/leetcodeSub")
                //.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("link", validLink))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Leetcode link saved"));
    }

    @Test
    public void testLeetcodeSubmission_InvalidLink() throws Exception {
        String invalidLink = "https://example.com";

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/challenge/leetcodeSub")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("link", invalidLink))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                //.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid link"));
    }
}
