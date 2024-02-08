package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class UserRegistrationController {

	@Autowired
	private UserService userService;


	@GetMapping("/registration")
	public String showRegistrationPage() {
		return "registration.html";
	}


//	@PostMapping("/registration")
//	@CrossOrigin(origins = "http://localhost:3000")
//	public String registerUserAccount(@ModelAttribute("User") User user) {
//		userService.save(user);
//		return "redirect:/login";
//	}

	@PostMapping(value = "/registration")
	public ResponseEntity<Object> registerUserAccount(@RequestBody User user) {
		System.out.println(user.getId());
		System.out.println(user.getFirstName());
		System.out.println(user.getEmail());
		User existingUser = userService.findUserByEmail(user.getEmail());
		if (existingUser != null) {
			String json = "{\"message\": \"User with this email already exists\"}";
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

}
