package com.data.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.configurations.IAuthenticationFacade;
import com.data.model.Product;
import com.data.model.User;
import com.data.service.ProductService;
import com.data.service.UserService;

@Controller
public class TestController {
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    UserDetailsService userDetailsService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(HttpServletRequest request) {
    	 if(request.isUserInRole("ROLE_ADMIN")) {
    		 return "true";
    	 }
    	return "false";
    }
}
