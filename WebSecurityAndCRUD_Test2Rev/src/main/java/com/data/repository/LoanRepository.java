package com.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.data.model.Loan;
import com.data.model.Product;

public interface LoanRepository extends JpaRepository <Loan,Long> {
	
	//probably won't work, but worth a shot
	@Query(value="select loans.product_id from loans inner join user on loans.borrower_id=user.id where user.username=?1", nativeQuery=true)
	public List <Long> findProductByBorrowerName(String name);

	
	public List<Loan> findAllByProductId(Long id);


	public List<Loan> findAllByBorrowerId(Long uId);
}
