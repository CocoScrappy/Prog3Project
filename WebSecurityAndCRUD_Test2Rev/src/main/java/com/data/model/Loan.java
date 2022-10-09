package com.data.model;



import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="loans")
@Entity
public class Loan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long loanId;
	@ManyToOne
	private User borrower;
	@ManyToOne(fetch = FetchType.EAGER, cascade = {PERSIST, MERGE})
	private Product product;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date loanStart;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date loanEnd;

	
	
	public Loan(User borrower, Product product, Date loanStart, Date loanEnd) {
		super();
		this.borrower = borrower;
		this.product = product;
		this.loanStart = loanStart;
		this.loanEnd = loanEnd;
	}
	
	public Loan() {
		super();
	}
	
	
	public long getLoanId() {
		return loanId;
	}

	public void setLoanId(long loanId) {
		this.loanId = loanId;
	}

	public User getBorrower() {
		return borrower;
	}

	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getLoanStart() {
		return loanStart;
	}

	public void setLoanStart(Date loanStart) {
		this.loanStart = loanStart;
	}

	public Date getLoanEnd() {
		return loanEnd;
	}

	public void setLoanEnd(Date loanEnd) {
		this.loanEnd = loanEnd;
	}

	
	

}
