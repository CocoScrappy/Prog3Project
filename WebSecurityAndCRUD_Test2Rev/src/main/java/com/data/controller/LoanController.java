package com.data.controller;

import java.sql.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.model.Loan;
import com.data.model.Product;
import com.data.model.User;
import com.data.repository.ProductRepository;
import com.data.service.LoanService;
import com.data.service.ProductService;
import com.data.service.UserService;

@RequestMapping("/loans")
@Controller
public class LoanController {
	
	@Autowired
	LoanService loanService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;

	@ResponseBody
	@RequestMapping("/all")
	public String getLoansByUserId(Model model){
		List<Loan> loanList=loanService.findAllByBorrowerId();
		model.addAttribute("loanList",loanList);
		return "loans";
	}
	
	@RequestMapping("/borrow/{prod_id}")
	public String createLoan(@PathVariable(name="prod_id") Long prod_id) {
		loanService.createLoan(prod_id);
		return "redirect:/products/all-products";
		
	}
	
	@RequestMapping("/return/{loan_id}")
	public String returnLoan(@PathVariable(name="loan_id") long loanId) {
		Loan loan= loanService.findById(loanId);
		
		return "redirect:/loans/all";
	}
}
