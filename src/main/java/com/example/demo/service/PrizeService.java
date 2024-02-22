package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Challenge;
import com.example.demo.repository.ChallengeRepository;
import com.example.demo.repository.PrizeRepository;


@Service
public class PrizeService {

    @Autowired
    private PrizeRepository prizeRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    public boolean canClaimPrize(String email, Long id) {

        List<Object[]> unclaimedPrizes = prizeRepository.findAllUnclaimedChallengesWithPrizesByUserEmailAndPrizeType(email, id);
        System.out.println("unclaimedPrizes: " + unclaimedPrizes.size());
        for (Object[] prizeData : unclaimedPrizes) {
            System.out.println("Prize ID: " + prizeData.length);
            System.out.println("Prize ID: " + prizeData[0]);
            System.out.println("Prize Type: " + prizeData[1]);
            System.out.println("Prize Type: " + prizeData[2]);
            System.out.println("Prize Type: " + prizeData[3]);
            System.out.println("Prize Type: " + prizeData[4]);
            System.out.println("Prize Type: " + prizeData[5]);
            System.out.println("Prize Type: " + prizeData[6]);
            System.out.println("Prize Type: " + prizeData[7]);
            Long challengeId = (Long) prizeData[0];
            Optional<Challenge> optionalChallenge = challengeRepository.findById(challengeId);
            if (optionalChallenge.isPresent()) {
                Challenge challenge = optionalChallenge.get();
                challenge.setClaimed(true);
                challengeRepository.save(challenge);
            } else {
                System.out.println("Something went wrong.");
            }
        }

        if (!unclaimedPrizes.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }
}