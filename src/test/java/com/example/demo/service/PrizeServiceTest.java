package com.example.demo.service;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Challenge;
import com.example.demo.model.User;
import com.example.demo.repository.ChallengeRepository;
import com.example.demo.repository.PrizeRepository;

@ExtendWith(MockitoExtension.class)
public class PrizeServiceTest {
	
	@Mock
    private PrizeRepository prizeRepository;

    @Mock
    private ChallengeRepository challengeRepository;

    @InjectMocks
    private PrizeService prizeService;

    @BeforeEach
    void setUp() {
        reset(prizeRepository, challengeRepository);
    }
	
	@Test
    public void testCanClaimPrize_Success() {
        // Arrange
        String email = "test@example.com";
        Long id = 2L;
        Object[] queryResult = new Object[] {1L, "leetcode.com/submissions/detail/test123", "leetcode submission link", id, email};
        List<Object[]> unclaimedPrizes = new ArrayList<Object[]>();
        unclaimedPrizes.add(queryResult);
        when(prizeRepository.findAllUnclaimedChallengesWithPrizesByUserEmailAndPrizeType(email, id)).thenReturn(unclaimedPrizes);

        User user = User.builder().email("test@test.com").password("test").firstName("first").lastName("last").build();
        Optional<Challenge> challenge = Optional.of(Challenge.builder().link("leetcode.com/submissions/detail/test123").type("leetcode submission link").user(user).prize(prizeRepository.getReferenceById(id)).build());
        when(challengeRepository.findById(1L)).thenReturn(challenge);

        // Act
        boolean result = prizeService.canClaimPrize(email, id);

        // Assert
        assertThat(result).isTrue();
        verify(prizeRepository).findAllUnclaimedChallengesWithPrizesByUserEmailAndPrizeType(email, id);
        verify(challengeRepository).findById(1L);
        verify(challengeRepository).save(Mockito.any(Challenge.class));
    }
	
	@Test
	public void testCanClaimPrize_NoUnclaimedPrizes_Failure() {
	    // Arrange
	    String email = "test@example.com";
	    Long id = 2L;
	    when(prizeRepository.findAllUnclaimedChallengesWithPrizesByUserEmailAndPrizeType(email, id)).thenReturn(Collections.emptyList());

	    // Act
	    boolean result = prizeService.canClaimPrize(email, id);

	    // Assert
	    assertThat(result).isFalse();
	    verify(prizeRepository).findAllUnclaimedChallengesWithPrizesByUserEmailAndPrizeType(email, id);
	    verifyNoInteractions(challengeRepository);
	}
	
	
	
	
}
