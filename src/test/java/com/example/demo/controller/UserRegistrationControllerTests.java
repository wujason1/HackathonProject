package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.User;
import com.example.demo.repository.ChallengeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@WebMvcTest
@ContextConfiguration(classes = UserRegistrationController.class) 
@AutoConfigureMockMvc(addFilters=false)
public class UserRegistrationControllerTests {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    
    private User user;
    
    @BeforeEach
    public void setUp() {
        this.user = new User();
        this.user.setEmail("test@example.com");
        this.user.setPassword("password");
    }

    @Test
    public void testShowRegistrationPage() throws Exception {
        mockMvc.perform(get("/user/registration"))
               .andExpect(status().isOk())
               .andExpect(content().json("{\"message\": \"Registration Endpoint Works\"}"));
    }
    
    @Test
    public void testRegisterUserAccount_NewUser_Success() throws Exception {
        when(userService.findUserByEmail(Mockito.any(String.class))).thenReturn(null, user);
        when(userService.save(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"test@example.com\", \"password\": \"password\"}"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().json("{\"email\": \"test@example.com\", \"password\": \"password\"}"));
    }
    
    @Test
    public void testRegisterUserAccount_ExistingUser_Error() throws Exception {
        when(userService.findUserByEmail(user.getEmail())).thenReturn(user);

        mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"test@example.com\", \"password\": \"password\"}"))
               .andExpect(status().isBadRequest())
               .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
               .andExpect(content().encoding(StandardCharsets.UTF_8))
               .andExpect(content().json("{\"message\": \"User with this email already exists\"}"));
    }
    
    @Test
    public void testRegisterUserAccount_FailedToSave() throws Exception {
        when(userService.findUserByEmail(user.getEmail())).thenReturn(null);

        mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"test@example.com\", \"password\": \"password\"}"))
               .andExpect(status().isInternalServerError())
               .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
               .andExpect(content().encoding(StandardCharsets.UTF_8))
               .andExpect(content().json("{\"message\": \"Saving User Failed\"}"));
    }
}
