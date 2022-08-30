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
	
	public Product getById(long Id) {
		return repo.findById(Id).get();
	}
	
	public List<Product> getProductsByUserId(long userId) {
		
		return repo.findByUserId(userId);
	}
	
	public void save(Product product) {
		
		repo.save(product);
	}

	public void deleteById(long id) {
		
		repo.deleteById(id);
		
	}
	
	
}
