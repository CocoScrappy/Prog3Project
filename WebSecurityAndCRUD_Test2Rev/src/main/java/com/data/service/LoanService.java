package com.data.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.model.Loan;
import com.data.model.Product;
import com.data.model.User;
import com.data.repository.LoanRepository;


@Service
public class LoanService {
	
	@Autowired
	UserService userService;
	@Autowired
	LoanRepository	loanRepo;
	@Autowired
	ProductService productService;

	public List<Loan> findAllByBorrowerId() {
		User currentUser=userService.getPrincipalUser();
		Long uId=currentUser.getId();
		return loanRepo.findAllByBorrowerId(uId);
	}

	public void createLoan(Long prod_id) {
		Product prod=productService.findById(prod_id).orElse(null);
		User borrower=userService.getPrincipalUser();
		borrower.setConfirmPassword("MustNotBeNullToPassValidation");
		prod.getUser().setConfirmPassword("AlsoMustNotBeEmptyLestYouShouldAngerTheGods");
		
		//gets current date
		Date loanStart= new java.sql.Date(new java.util.Date().getTime());
		
		Loan newLoan = new Loan(borrower,prod,loanStart,null);
		loanRepo.save(newLoan);
		
	}

	public Loan findById(long loanId) {
		Loan loan=loanRepo.findById(loanId).orElse(null);
		loan.setLoanEnd(new Date(new java.util.Date().getTime()));
		loanRepo.save(loan);
		return null;
	}

}
