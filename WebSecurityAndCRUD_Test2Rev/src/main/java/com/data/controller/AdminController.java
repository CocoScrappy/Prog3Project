package com.data.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.data.exceptions.DatabaseException;
import com.data.model.User;
import com.data.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
	public String showUsers(Model model) {
		try {
		List <User> listUsers = userService.getAllUsers();
		model.addAttribute("listUsers", listUsers);
		return "users";
		} catch (DatabaseException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "users";
		}
	}
	
	@RequestMapping("/edit/user/{id}")
	public ModelAndView showEditUserPage(@PathVariable(name="id") long id)
	{
		ModelAndView mav =  new ModelAndView("edit_user");
		User user = userService.getUserById(id);
		mav.addObject("user", user);
		
		return mav;
	}
	
	@PostMapping("/user/save")
	public String processRegister(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
		
		if (bindingResult.hasErrors()) {
			return "edit_user";
		}
		
		try {
			userService.saveUser(user);
			redirectAttrs.addAttribute("msg", "User edited successfully");
			return "redirect:/admin/users";
		} catch (Exception e) {
			bindingResult.addError(new ObjectError("globalError", "Something went wrong, please check your input"));
			return "edit_user";
		}

	}
	
	@RequestMapping("/delete/user/{id}")
	public String deleteUser(@PathVariable(name="id") long id, RedirectAttributes redirectAttrs)
	{
		userService.deleteUserById(id);
		redirectAttrs.addAttribute("msg", "User deleted successfully");
		return "redirect:/admin/users";
	}
	
}
