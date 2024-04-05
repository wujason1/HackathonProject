package com.example.demo.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.model.Challenge;
import com.example.demo.model.User;
import com.example.demo.service.ChallengeServiceTest;

@SpringBootTest
@ActiveProfiles("test")
public class PrizeRepositoryTests {
	
	@Autowired
    private PrizeRepository prizeRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private ChallengeRepository challengeRepository;
	
	@AfterEach
    public void tearDown() {
		challengeRepository.deleteAll();
		userRepository.deleteAll();
    }
	
	@Test
	public void findAllUnclaimedChallengesWithPrizesByUserEmailAndPrizeType() {
		// Arrange
		User user = User.builder().email("test@test.com").password("test").firstName("first").lastName("last").build();
		User savedUser = userRepository.save(user);
		
		Challenge challenge = Challenge.builder().link("leetcode.com/submissions/detail/test123").type("leetcode submission link").user(savedUser).prize(prizeRepository.getReferenceById(2L)).build();
		challengeRepository.save(challenge);
		
        // Act
        List<Object[]> result = prizeRepository.findAllUnclaimedChallengesWithPrizesByUserEmailAndPrizeType("test@test.com", 2L); 

        // Assert
        Assertions.assertThat(result.size()).isEqualTo(1);
	}
}
