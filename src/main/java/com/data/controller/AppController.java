package com.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

//	Index
	@GetMapping("/")
	public String viewHomePage() {
		return "index";
	}

//	Login Page
	@GetMapping("/login")
	public String viewLogin() {
		return "login";
	}
	
//	@GetMapping("/users")
//	public String listUsers(Model model) {
//		List<User> listUsers = userRepo.findAll();
//		model.addAttribute("listUsers", listUsers);
//		return "users";
//	}

}
