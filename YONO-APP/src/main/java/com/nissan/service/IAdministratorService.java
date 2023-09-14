package com.nissan.service;

import java.util.List;

import com.nissan.model.Customer;

public interface IAdministratorService {
	//to add new customer
	public Customer saveCustomer(Customer customer);
	
	//to list customers
	public List<Customer> getCustomer();
	
	//to delete a customer
	public void deleteCustomer(long accno);
	
	//to update customer
	public Customer updateCustomer(Customer customer,Customer customerNew);
	
	//to get a specific customer
	public Customer getCustomer(long accno);
}
