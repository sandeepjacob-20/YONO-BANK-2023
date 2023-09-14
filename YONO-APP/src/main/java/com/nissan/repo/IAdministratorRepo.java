package com.nissan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissan.model.Customer;

@Repository
public interface IAdministratorRepo extends CrudRepository<Customer,Long>{
	
	//custom delete operation
	@Modifying
	@Query("UPDATE com.nissan.model.Customer SET isActive=0 WHERE accNo=?1")
	public Customer deleteCustomer(long accNo);
	
	@Query("From Customer WHERE isActive=1")
	public List<Customer> listAll();
}
