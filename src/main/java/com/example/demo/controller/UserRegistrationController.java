package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("User") User user) {
		userService.save(user);
		return "redirect:/login";
	}
}
