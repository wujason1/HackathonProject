package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@InjectMocks
    private UserService userService;
	
	@Mock
    private UserRepository userRepository;
	
	@BeforeEach
    void setUp() {
        reset(userRepository);
    }
	
	@Test
    public void testSaveUser() {
        // Arrange
        User user = User.builder().email("test@test.com").password("test").firstName("first").lastName("last").build();
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = userService.save(user);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(user);
        verify(userRepository, times(1)).save(user);
    }
	
	@Test
    public void testFindUserByEmailAndPassword() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        User expectedUser = User.builder().email(email).password(password).firstName("first").lastName("last").build();
        when(userRepository.findUserByEmailAndPassword(email, password)).thenReturn(expectedUser);

        // Act
        User result = userService.findUserByEmailAndPassword(email, password);

        // Assert
        assertThat(result).isEqualTo(expectedUser);
        verify(userRepository).findUserByEmailAndPassword(email, password);
    }
	
	@Test
    public void testFindUserByEmail() {
        // Arrange
        String email = "test@example.com";
        User expectedUser = User.builder().email(email).password("password").firstName("first").lastName("last").build();
        when(userRepository.findUserByEmail(email)).thenReturn(expectedUser);

        // Act
        User result = userService.findUserByEmail(email);

        // Assert
        assertThat(result).isEqualTo(expectedUser);
        verify(userRepository).findUserByEmail(email);
    }
}
