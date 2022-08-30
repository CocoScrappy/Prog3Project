package com.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String specs;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

	public Product() {

	}

	public Product(Long id, String name, String specs) {
		this.id = id;
		this.name = name;
		this.specs = specs;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", specs=" + specs + ", user_id=" + user.getId() + "]";
	}
	
	

}
