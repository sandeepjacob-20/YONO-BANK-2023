package com.nissan.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissan.model.Customer;

@Repository
public interface ICustomerRepo extends CrudRepository<Customer,Long> {
	
	//to deposit amount to account
	@Modifying
	@Query("UPDATE com.nissan.model.Customer SET balance=balance+?1 WHERE accNo=?2")
	public void depositAmount(float amount, long accNo);
	
	//to get the balance of user
	@Query("SELECT balance FROM com.nissan.model.Customer WHERE accNo=?1")
	public float getBalance(long accNo);
	
	//to get the minimum balance of an account
	@Query("SELECT minBalance FROM com.nissan.model.Customer WHERE accNo=?1")
	public float getMinBalance(long accNo);
	
	//to withdraw amount from an account
	@Modifying
	@Query("UPDATE com.nissan.model.Customer SET balance=balance-?1 WHERE accNo=?2")
	public void withdraw(float amount, long accNo);

	//to debit money from account
	@Modifying
	@Query("UPDATE com.nissan.model.Customer SET balance=balance-?2 WHERE accNo=?1")
	public void debitFrom(long fromAcc, float amount);
	
	//to credit money into account
	@Modifying
	@Query("UPDATE com.nissan.model.Customer SET balance=balance+?2 WHERE accNo=?1")
	public void creditTo(long toAcc, float amount);
	
}
