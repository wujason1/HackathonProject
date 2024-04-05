package com.example.demo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Challenge;
import com.example.demo.model.Prize;
import com.example.demo.model.User;
import com.example.demo.repository.ChallengeRepository;
import com.example.demo.repository.PrizeRepository;
import com.example.demo.repository.UserRepository;

@Service
public class ChallengeService {
    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PrizeRepository prizeRepository;

    public Challenge saveResume(String type, MultipartFile fileData, Authentication authentication) throws Throwable {
        Challenge challenge = new Challenge();
        String username = authentication.getName();
        User user = userRepository.findUserByEmail(username);
        challenge.setUser(user);
        challenge.setFile(fileData.getBytes());
        challenge.setType(type);
        Prize prize = prizeRepository.getById(1L);
        challenge.setPrize(prize);
        return challengeRepository.save(challenge);
    }

    public Challenge saveLink(String type, String link, Authentication authentication) {
        Challenge challenge = new Challenge();
        String username = authentication.getName();
        User user = userRepository.findUserByEmail(username);
        challenge.setUser(user);
        challenge.setLink(link);
        challenge.setType(type);
        Prize prize = null;
        if(type.equals("leetcode submission link")) {
            prize = prizeRepository.getById(2L);
        }
        if(type.equals("certification link")) {
            prize = prizeRepository.getById(3L);
        }
        challenge.setPrize(prize);
        return challengeRepository.save(challenge);
    }
    
    @Scheduled(cron = "0 0 0 * * MON")
    public void eraseChallengesTable() {
        System.out.println("Erasing challenges table...");
        challengeRepository.deleteAll();
    }



}
