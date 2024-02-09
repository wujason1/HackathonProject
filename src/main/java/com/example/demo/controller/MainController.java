package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login.html";
	}

	@PostMapping(value = "/login")
	public ResponseEntity<Object> registerUserAccount(HttpServletRequest request, HttpServletResponse response, @RequestParam String email, @RequestParam String password) throws Exception {
		User expectedUser = userService.findUserByEmailAndPassword(email, password);
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		if(expectedUser==null) {
			String json = "{\"error\": \"Incorrect Credentials\"}";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		} else {
			String json = "{\"message\": \"main endpoint works\"}";
			return ResponseEntity.status(HttpStatus.OK).body(json);
		}
	}

	@GetMapping("/loginerror")
	public ResponseEntity<Object> errorPage() {
		String json = "{\"error\": \"Incorrect Credentials\"}";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
	}


	@GetMapping("main")
	public ResponseEntity<Object> mainPage() {
		String json = "{\"message\": \"main endpoint works\"}";
		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

}


