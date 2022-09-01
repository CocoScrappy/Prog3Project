package com.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.data.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p")
	public List<Product> findAll();
	
	@Query("SELECT p FROM User u JOIN u.products p WHERE p = ?1")
	public List<Product> findByUserId(Long userId);
	
	@Query("SELECT p FROM Product p WHERE p.id = ?1")
	public void deleteByProductId(Long id);
	
	@Query("UPDATE Product p SET p.id = ?1")
	public void setOnLoanStatus(Long id);
	
	
//    "SELECT d FROM Employee e JOIN e.department d", Department.class);
//List<Department> resultList = query.getResultList();
//
//TypedQuery<Phone> query 
//= entityManager.createQuery(
//    "SELECT ph FROM Employee e JOIN e.phones ph WHERE ph LIKE '1%'", Phone.class);
//List<Phone> resultList = query.getResultList();

}
