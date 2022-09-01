package com.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.model.Product;
import com.data.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	public Product getById(Long Id) {
		return repo.findById(Id).get();
	}
	
	public List<Product> getAllProducts() {
		return repo.findAll();
	}
	
	public List<Product> getProductsByUserId(Long userId) {
		return repo.findByUserId(userId);
	}
	
	public void save(Product product) {
		repo.save(product);
	}

	public void deleteByProductId(Long id) {
		repo.deleteByProductId(id);
	}

	public void setOnLoanStatus(Long id) {
		repo.setOnLoanStatus(id);
	}
}
