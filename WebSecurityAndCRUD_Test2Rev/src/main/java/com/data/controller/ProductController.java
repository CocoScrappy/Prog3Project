package com.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.data.model.Product;
import com.data.model.User;
import com.data.service.ProductService;
import com.data.service.UserService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all-products")
	public String exploreAllProducts(Model model) {
		List<Product>  products = service.getAllProducts();
		model.addAttribute("products", products);
		return "products";
	}
	
	@GetMapping("/my-products")
	public String showUserProducts(Model model) {
		User currentUser = userService.getPrincipalUser();
		List<Product>  products = service.getProductsByUserId(currentUser.getId());
		model.addAttribute("products", products);
		return "products";
	}
	
	@RequestMapping("/new-product")
	public String showNewProductPage(Model model)
	{
		Product product= new Product();
		model.addAttribute("product", product);
		return "new_product";
	}
	
	@PostMapping("/save-product")
	public String saveProduct(@ModelAttribute("product") Product product)
	{
		User currentUser = userService.getPrincipalUser();
		product.setOwner(currentUser);
		service.save(product);
		return "redirect:/products/";
	}
	
	@RequestMapping("/edit-product/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name="id") Long id)
	{
		ModelAndView mav =  new ModelAndView("edit_product");
		Product product = service.getById(id);
		mav.addObject("product", product);
		
		return mav;
	}
	
	@RequestMapping("/delete-product/{id}")
	public String deleteProduct(@PathVariable(name="id") Long id)
	{
		service.deleteByProductId(id);
		return "redirect:/products";
	}

}
