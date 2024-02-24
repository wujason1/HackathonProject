package com.example.demo.controller;


import java.io.IOException;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpVersion;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Challenge;
import com.example.demo.service.ChallengeService;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @PostMapping("/resume")
    public ResponseEntity<Object> resumeSubmission(@RequestParam("file") MultipartFile fileData) throws Throwable {
        challengeService.saveResume("resume", fileData, SecurityContextHolder.getContext().getAuthentication());
        String responseMessage = "{\"message\": \"File uploaded successfully\"}";
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    //sub = submission
    @PostMapping("/leetcodeSub")
    public ResponseEntity<Object> leetcodeSubmission(@RequestParam("link") String link) throws Throwable {
        if(link.contains("leetcode.com/submissions/detail")) {
            challengeService.saveLink("leetcode submission link", link, SecurityContextHolder.getContext().getAuthentication());
            String json = "{\"message\": \"Leetcode link saved\"}";
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } else {
            String json = "{\"message\": \"Invalid link\"}";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

    }

    //cert = certificate
    @PostMapping("/linkedinCert")
    public ResponseEntity<Object> linkedinCertification(@RequestParam("link") String link) throws Throwable {
        if(link.contains("skillshop.credential.net")) {
            challengeService.saveLink("certification link", link, SecurityContextHolder.getContext().getAuthentication());
            String json = "{\"message\": \"Certification link saved\"}";
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } else {
            String json = "{\"message\": \"Invalid link\"}";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

    }
}
