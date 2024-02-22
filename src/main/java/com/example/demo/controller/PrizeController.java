package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.PrizeRepository;
import com.example.demo.service.PrizeService;

@RestController
public class PrizeController {

    @Autowired
    private PrizeService prizeService;

    @GetMapping("/prizes/{id}")
    public ResponseEntity<Object> getFirstPrize(@PathVariable Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        boolean result = prizeService.canClaimPrize(userEmail, id);

        if(result) {

//            String json = "{\"message\": \"Prize claimed\"}";
            String json = "{\"message\": true}";
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } else {
            //could be because already claimed or user did not do the challenge needed to claim the prize
//            String json = "{\"message\": \"Prize can't be claimed\"}";
            String json = "{\"message\": false}";
            return ResponseEntity.status(HttpStatus.OK).body(json);
        }
    }
}
