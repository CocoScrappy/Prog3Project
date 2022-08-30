package com.data.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.data.model.User;
import com.data.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	

	@Autowired
	private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
	}

	
//	Register new user
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}

//	Registration processing
	@PostMapping("/registration")
	public String processRegister(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
		
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		if(user.getPassword().length() > 16 || user.getPassword().length() < 8) {
			bindingResult.rejectValue("password", "size.password", "Password should be 8-16 characters long");
			return "signup_form";
		}
//		
//		
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			bindingResult.rejectValue("confirmPassword", "mismatch.confirmPassword", "Password confirmation mismatch");
			return "signup_form";
		}
		
		
		try {
			userService.saveUser(user);
			return "register_success";
		} catch (Exception e) {
			bindingResult.rejectValue("username", "exists.username" ,e.getMessage());
			return "signup_form";
		}	
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPassword = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encodedPassword);
//		
//		userRepo.save(user);
//		return "register_success";

	}

}
