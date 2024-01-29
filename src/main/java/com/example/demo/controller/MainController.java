package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@PostMapping("/login")
	public String registerUserAccount(@RequestParam String email, @RequestParam String password) {
		//(@ModelAttribute("User") User user
		//String email = user.getEmail();
		//String password = user.getPassword();
		User expectedUser = userService.findUserByEmailAndPassword(email, password);
		if(expectedUser==null) {
			//never gets here, if entering wrong credentials, gives 405 in browser and 
			//Request method 'POST' is not supported in console
			return "error.html";
		} else {
			return "redirect:/main";
		}
	}
	
	@GetMapping("main")
	public String mainPage() {
		return "mainPage.html";
	}
}


