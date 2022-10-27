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
import com.data.repository.ProductRepository;
import com.data.service.LoanService;
import com.data.service.ProductService;
import com.data.service.UserService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoanService loanService;
	
	
	//show items from across the platform(no matter whether onloan or not)
	@GetMapping("/all-products")
	public String exploreAllProducts(Model model) {
		List<Product>  products = productService.getAllProductsNotOnLoan();
		model.addAttribute("products", products);
		return "products";
	}
	
	//show items of the owner provided you have access to user object
	@GetMapping("/my-products")
	public String showUserProducts(Model model) {
		User currentUser = userService.getPrincipalUser();
		List<Product>  products = productService.getProductsByUserId(currentUser.getId());
		model.addAttribute("products", products);
		return "my_products";
	}
	
	@RequestMapping("/new-product")
	public String showNewProductPage(Model model)
	{
		Product product= new Product();
		model.addAttribute("product", product);
		return "new_product";
	}
	
	
	// owner adding product to the platform
	@PostMapping("/save-product")
	public String saveProduct(@ModelAttribute("product") Product product)
	{
		User currentUser = userService.getPrincipalUser();
		product.setOwner(currentUser);
		productService.save(product);
		return "redirect:/products/all-products";
	}
	
	@RequestMapping("/delete-product/{id}")
	public String deleteProduct(@PathVariable(name="id") Long id)
	{
		productService.deleteByProductId(id);
		return "redirect:/products/my-products";
	}
	
	@RequestMapping("/borrow/{id}")
	public String borrowProduct(@PathVariable(name="id") Long id)
	{
		Product prod=productService.getById(id);
		prod.setOnLoan(true);
		productService.save(prod);
//		productService.setOnLoanStatus(id);
		return "redirect:/loans/borrow/{id}";
	}

}
