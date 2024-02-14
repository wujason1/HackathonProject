package com.example.demo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Challenge;
import com.example.demo.repository.ChallengeRepository;

@Service
public class ChallengeService {
    @Autowired
    private ChallengeRepository challengeRepository;

    public Challenge saveResume(String type, MultipartFile fileData) throws Throwable {
        Challenge challenge = new Challenge();
        challenge.setFile(fileData.getBytes());
        challenge.setType(type);
        return challengeRepository.save(challenge);
    }

    public Challenge saveLink(String type, String link) {
        Challenge challenge = new Challenge();
        challenge.setLink(link);
        challenge.setType(type);
        return challengeRepository.save(challenge);
    }



}
