package com.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.data.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("Select p FROM Product p WHERE p.user.id = ?1")
	public List<Product> findByUserId(long userId);
	
	public Optional<Product> findById(Long id);

}
