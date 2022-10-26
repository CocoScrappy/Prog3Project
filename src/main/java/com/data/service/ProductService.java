package com.data.service;

import java.util.List;
import java.util.Optional;

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
	
	public List<Product> getAllProductsNotOnLoan() {
		return repo.findAllProductsNotOnLoan();
	}
	
	public List<Product> getProductsByUserId(Long userId) {
		return repo.findByUserId(userId);
	}
	
	public void save(Product product) {
		repo.save(product);
	}

	public void deleteById(long id) {
		
		repo.deleteById(id);
		
	}
	
	public void deleteByProductId(Long id) {
		repo.deleteById(id);
	}

	public void setOnLoanStatus(Long id) {
		repo.setOnLoanStatus(id);
	}

	public Optional<Product> findById(Long prod_id) {
		
		return repo.findById(prod_id);
	}
	
	
}
