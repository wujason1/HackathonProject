package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.model.User;
import com.example.demo.repository.ChallengeRepository;
import com.example.demo.repository.PrizeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.Challenge;
import com.example.demo.model.Prize;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

@ExtendWith(MockitoExtension.class)
public class ChallengeServiceTest {
	
	@Mock
    private ChallengeRepository challengeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PrizeRepository prizeRepository;

    @InjectMocks
    private ChallengeService challengeService;
    
    @Nested
    class ChallengeServiceTestWithSetup {
    	@BeforeEach
        void setUp() {
            // Mock authentication
            Authentication authentication = mock(Authentication.class);
            when(authentication.getName()).thenReturn("test@example.com");
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        @Test
        void testSaveLink_WithLeetCodeType() {
        	// Arrange
        	User user = User.builder().email("test@example.com").password("test").firstName("first").lastName("last").build();
            when(userRepository.findUserByEmail("test@example.com")).thenReturn(user);
            Prize prize = Prize.builder().type("prize2").build();
            when(prizeRepository.getById(2L)).thenReturn(prize);
            Challenge challenge = Challenge.builder().link("leetcode.com/submissions/detail/test123").type("leetcode submission link").user(user).prize(prize).build();
            when(challengeRepository.save(Mockito.any(Challenge.class))).thenReturn(challenge);
            
            // Act
            Challenge savedChallenge = challengeService.saveLink("leetcode submission link", "leetcode.com/submissions/detail/test123", SecurityContextHolder.getContext().getAuthentication());

            // Assert
            verify(challengeRepository).save(Mockito.any(Challenge.class));
            assertThat(savedChallenge.getType()).isEqualTo("leetcode submission link");
        }
        
        @Test
        void testSaveResume_Success() throws Throwable {
        	// Arrange
            User user = mock(User.class);
            Prize prize = mock(Prize.class);
            MultipartFile fileData = Mockito.mock(MultipartFile.class);

            when(userRepository.findUserByEmail("test@example.com")).thenReturn(user);
            when(prizeRepository.getById(1L)).thenReturn(prize);
            Challenge challenge = Challenge.builder().id(0L).type("resume").user(user).prize(prize).build();
            when(challengeRepository.save(Mockito.any(Challenge.class))).thenReturn(challenge);
            // Act
            Challenge savedChallenge = challengeService.saveResume("resume", fileData, SecurityContextHolder.getContext().getAuthentication());

            // Assert
            verify(userRepository).findUserByEmail("test@example.com");
            verify(prizeRepository).getById(1L);
            verify(challengeRepository).save(Mockito.any(Challenge.class));

            assertThat(savedChallenge).isNotNull();
            assertThat(savedChallenge.getId()).isEqualTo(user.getId());
            assertThat(savedChallenge.getType()).isEqualTo("resume");
            
        }
    }
    
    
    @Test
    void testEraseChallengesTable() {
        // Arrange: Create necessary mocks and dependencies if needed
    	
        // Act: Call the method directly
        challengeService.eraseChallengesTable();

        // Assert: Verify the expected behavior
        verify(challengeRepository).deleteAll();
    }

    
}
