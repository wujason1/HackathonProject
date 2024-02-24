package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.config.JwtService;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	UserDetailsService userDetailsService;

	@GetMapping("/")
	public ResponseEntity<Object> defaultEndpoint() {

		String json = "{\"message\": \"Welcome to the default endpoint!\"}";

		return ResponseEntity.status(HttpStatus.OK).body(json);

	}

	@GetMapping("/login")
	public ResponseEntity<Object> login() {

		String json = "{\"message\": \"Login endpoint works\"}";

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> registerUserAccount(HttpServletRequest request, @RequestParam(required = false) String email, @RequestParam(required = false) String password, @RequestHeader(value="Authorization", required = false) String token) throws Exception {
		if(token!=null) {
			final String userEmail = jwtService.extractUsername(token.substring(7));
			if (userEmail != null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
				if(jwtService.isTokenValid(token.substring(7), userDetails)) {
					String json = "{\"message\": \"valid token, logged in\"}";
					return ResponseEntity.status(HttpStatus.OK).body(json);
				}
			}
		}
		User expectedUser = userService.findUserByEmailAndPassword(email, password);
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		if(expectedUser==null) {
			String json = "{\"error\": \"Incorrect Credentials\"}";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		} else {
			String jwtToken = jwtService.generateToken(expectedUser);
			String json = "{\"message\": \"token generated, logged in\","
					+ "		\"token\":" + "\"" + jwtToken + "\"" + "}";
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


