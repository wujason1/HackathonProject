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
        System.out.println(fileData.getSize());
        System.out.println(fileData.getOriginalFilename());
        challengeService.saveResume("resume", fileData, SecurityContextHolder.getContext().getAuthentication());
        String responseMessage = "{\"message\": \"File uploaded successfully\"}";
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    //sub = submission
    @PostMapping("/leetcodeSub")
    public ResponseEntity<Object> leetcodeSubmission(@RequestParam("link") String link) throws Throwable {
        if(link.contains("leetcode.com/submissions/detail")) {
//			CloseableHttpClient httpClient = HttpClients.createDefault();
//	        HttpUriRequestBase request = new HttpGet(link);
//	        request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36 Edg/121.0.0.0");
//	        request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
//	        CloseableHttpResponse response = httpClient.execute(request);
//	        int statusCode = response.getCode();
//	        String responseBody = EntityUtils.toString(response.getEntity());
//	        System.out.println("Response Body:");
//	        System.out.println(responseBody);
//	        System.out.println(statusCode);

            challengeService.saveLink("leetcode submission link", link, SecurityContextHolder.getContext().getAuthentication());
            String json = "{\"message\": \"Leetcode link saved\"}";
            return ResponseEntity.status(HttpStatus.OK).body(json);

//	        else {
//	        	String json = "{\"message\": \"Page Not Found\"}";
//		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
//	        }

        } else {
            String json = "{\"message\": \"Invalid link\"}";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

    }

    //cert = certificate
    @PostMapping("/linkedinCert")
    public ResponseEntity<Object> linkedinCertification(@RequestParam("link") String link) throws Throwable {
        if(link.contains("skillshop.credential.net")) {
            //String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36 Edg/121.0.0.0";
//	        CloseableHttpClient httpClient = HttpClients.createDefault();
//	        HttpUriRequestBase request = new HttpGet(link);
            //request.addHeader(":authority", "skillshop.credential.net");
            //request.addHeader(":method", "GET");
            //request.addHeader(":path", "/acc406e0-2a70-4176-a53f-777be4e8fbef1");
            //request.addHeader(":scheme", "https");
            //request.addHeader("Accept-Encoding", "gzip, deflate, br");
            //request.addHeader("Accept-Language", "en-US,en;q=0.9");
            //request.addHeader(":Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
            //request.addHeader("User-Agent", userAgent);
            //request.setVersion(HttpVersion.HTTP_1_0);
            //request.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            //request.addHeader("Pragma", "no-cache");
            //request.addHeader("Expires", "Tue, 01 Jan 1980 1:00:00 GMT");

//	        System.out.println("Request Headers:");
//	        for (Header header : request.getHeaders()) {
//	            System.out.println(header.getName() + ": " + header.getValue());
//	        }
//
//	        CloseableHttpResponse response = httpClient.execute(request);
//	        String responseBody = EntityUtils.toString(response.getEntity());
//	        System.out.println("Response Body:");
//	        System.out.println(responseBody);
//
//	        System.out.println("Response Headers:");
//	        for (Header header : response.getHeaders()) {
//	            System.out.println(header.getName() + ": " + header.getValue());
//	        }

            challengeService.saveLink("certification link", link, SecurityContextHolder.getContext().getAuthentication());
            String json = "{\"message\": \"Certification link saved\"}";
            return ResponseEntity.status(HttpStatus.OK).body(json);

        } else {
            String json = "{\"message\": \"Invalid link\"}";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

    }
}
