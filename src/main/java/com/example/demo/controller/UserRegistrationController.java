package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {

	@Autowired
	private UserService userService;

	@GetMapping("/registration")
	public ResponseEntity<Object> showRegistrationPage() {
		String json = "{\"message\": \"Registration Endpoint Works\"}";

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	@PostMapping("/registration")
	public ResponseEntity<Object> registerUserAccount(@RequestBody User user) {
		User existingUser = userService.findUserByEmail(user.getEmail());
		if (existingUser != null) {
			String json = "{\"message\": \"User with this email already exists\"}";
			//return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(json);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		}

		userService.save(user);
		User savedUser = userService.findUserByEmail(user.getEmail());
		if(savedUser==null) {

			String json = "{\"message\": \"Saving User Failed\"}";

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);

		} else {
			return ResponseEntity.status(HttpStatus.OK).body(savedUser);
		}

	}

	@GetMapping("/edit")
	public ResponseEntity<Object> showEditForm() {

		String json = "{\"message\": \"Edit Endpoint Works\"}";

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	@PostMapping("/edit")
	public ResponseEntity<Object> updateUser(@RequestBody User user) {
		User expectedUser = userService.findUserByEmail(user.getEmail());
		if(expectedUser==null) {
			String json = "{\"message\": \"No user found\"}";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
		} else {
			expectedUser.setFirstName(user.getFirstName());
			expectedUser.setLastName(user.getLastName());
			expectedUser.setPassword(user.getPassword());
			userService.save(expectedUser);
			return ResponseEntity.status(HttpStatus.OK).body(expectedUser);
		}
	}
}
