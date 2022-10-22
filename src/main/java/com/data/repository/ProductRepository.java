package com.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.data.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	public Optional<Product> findById(Long id);

	@Query("SELECT p FROM Product p")
	public List<Product> findAll();
	
//	@Query("Select p FROM Product p WHERE p.user_id = ?1")
	@Query(value="select * from products where user_id=?1", nativeQuery=true)
	public List<Product> findByUserId(Long userId);
	

//	@Query("DELETE FROM Product p WHERE p.id = ?1")
	public void deleteById(Long id);
	
	@Query("UPDATE Product p SET p.id = ?1")
	public void setOnLoanStatus(Long id);
}
