package com.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import static javax.persistence.CascadeType.MERGE; 
import static javax.persistence.CascadeType.PERSIST;

@Table(name="products")
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String specs;
	private Boolean onLoan;
	private String photoUrl;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User owner;

	@OneToMany(fetch = FetchType.EAGER, cascade = {PERSIST, MERGE} ,mappedBy = "borrower")
	private Set<Loan> loans;
	
	
	public Product() {
	}

//	public Product(Long id, String name, String specs, Boolean onLoan, String photoUrl) {
//		this.id = id;
//		this.name = name;
//		this.specs = specs;
//		this.onLoan = onLoan;
//		this.photoUrl = photoUrl;
//	}

	public Product(Long id, String name, String specs, Boolean onLoan, String photoUrl, User owner) {
		super();
		this.id = id;
		this.name = name;
		this.specs = specs;
		this.onLoan = onLoan;
		this.photoUrl = photoUrl;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSpecs() {
		return specs;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}
	
	public Boolean getOnLoan() {
		return onLoan;
	}

	public void setOnLoan(Boolean onLoan) {
		this.onLoan = onLoan;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public Set<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Set<Loan> loans) {
		this.loans = loans;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", specs=" + specs + ", user_id=" + owner.getId() + "]";
	}
	
	

}
