package com.data.controller;

import java.util.List;

import javax.validation.Valid;

import com.data.exceptions.userAlreadyExistsException;
import com.data.model.Role;
import com.data.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
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

	@Autowired
	private RoleService roleService;

	@Autowired
	UserController usercontroller;
	
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

	@GetMapping("/new-user")
	public String showNewUserPage(Model model)
	{
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleService.initializeRoles());
		return "new_user";
	}

	@PostMapping("/new-user/save")
	public String processNewUser(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttrs, Model model) {

		List<Role> roles = roleService.initializeRoles();
		model.addAttribute("roles", roles);

		System.out.println(user);
		if (bindingResult.hasErrors()) {
			return "new_user";
		}

		System.out.println(user);
		if(user.getPassword().length() > 16 || user.getPassword().length() < 8) {
			bindingResult.rejectValue("password", "size.password", "Password should be 8-16 characters long");
			return "new_user";
		}

		if(user.getConfirmPassword().isEmpty()) {
			bindingResult.rejectValue("confirmPassword", "empty.confirmPassword", "Password Confirmation should not be empty");
			return "new_user";
		}

		System.out.println(user);
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			bindingResult.rejectValue("confirmPassword", "mismatch.confirmPassword", "Password confirmation mismatch");
			return "new_user";
		}

		try {
			System.out.println(user);
			userService.saveNewUser(user);
			redirectAttrs.addFlashAttribute("msg", "User created successfully");
			return "redirect:/admin/users";
		} catch (userAlreadyExistsException e) {
			bindingResult.rejectValue("username", "exists.username", e.getMessage());
			return "new_user";
		}

	}

	@RequestMapping("/edit/user/{id}")
	public ModelAndView showEditUserPage(@PathVariable(name="id") long id)
	{
		ModelAndView mav =  new ModelAndView("edit_user");
		mav.addObject("user", userService.getUserById(id));
		mav.addObject("roles", roleService.initializeRoles());
		
		return mav;
	}
	
	@PostMapping("/edit/user/save")
	public String processRegister(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttrs, Model model) {

		List<Role> roles = roleService.initializeRoles();
		model.addAttribute("roles", roles);

		if (bindingResult.hasErrors()) {
			return "edit_user";
		}

		try {
			userService.editUser(user);
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
