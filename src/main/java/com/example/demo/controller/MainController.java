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


//	@PostMapping("/login")
//	@CrossOrigin("http://localhost:3000")
////	public String registerUserAccount(@RequestParam String email, @RequestParam String password) {
//	public User registerUserAccount(@RequestParam String email, @RequestParam String password) {
//		//(@ModelAttribute("User") User user
//		//String email = user.getEmail();
//		//String password = user.getPassword();
//		User expectedUser = userService.findUserByEmailAndPassword(email, password);
//		if(expectedUser==null) {
//			//never gets here, if entering wrong credentials, gives 405 in browser and
//			//Request method 'POST' is not supported in console
//			return null;
//		} else {
//			return expectedUser;
//		}
//	}

	@PostMapping(value = "/login")
//	@CrossOrigin("http://localhost:3000")
	public void registerUserAccount(HttpServletRequest request, HttpServletResponse response, @RequestParam String email, @RequestParam String password) throws Exception {
//	public void registerUserAccount(@RequestParam String email, @RequestParam String password) throws Exception {
		User expectedUser = userService.findUserByEmailAndPassword(email, password);
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//		String baseUrl = "http://localhost:3000";
		if(expectedUser==null) {
			String url =  baseUrl + "/loginerror";
			System.out.println("url: " + url);
			response.sendRedirect(url);
		} else {
			String url =  baseUrl + "/main";
			System.out.println("url: " + url);
			response.sendRedirect(url);
		}
	}



	@GetMapping("main")
	public ResponseEntity<Object> mainPage() {

		String json = "{\"message\": \"main endpoint works\"}";

		return ResponseEntity.status(HttpStatus.OK).body(json);
	}



}


