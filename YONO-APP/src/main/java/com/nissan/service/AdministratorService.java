package com.nissan.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.common.Validation;
import com.nissan.model.Customer;
import com.nissan.repo.IAdministratorRepo;

@Service
public class AdministratorService implements IAdministratorService {

	@Autowired
	private IAdministratorRepo adminRepo;
	
	@Autowired
	private Validation validation;
	
	@Autowired
	private Generator generator;
	
	//to add customers
	@Override
	public Customer saveCustomer(Customer customer) {
		customer.setPin(generator.getPin());
		customer.setAccNo(Long.parseLong(generator.getAccountNo()));
		if(validation.isNameValid(customer.getCustName())
				&&validation.isNumberValid(customer.getMobileNumber())
			&&validation.isAccountValid(String.valueOf(customer.getAccNo()))) {
			return adminRepo.save(customer);
		}
		return null;
	}

	//to list all the customers
	@Override
	public List<Customer> getCustomer() {
		return (List<Customer>)adminRepo.listAll();
	}

	//delete a customer
	@Transactional
	@Override
	public Customer deleteCustomer(long accno) {
		if(validation.isAccountValid(String.valueOf(accno))) 
			return adminRepo.deleteCustomer(accno);
		return null;
	}

	//update customer details
	@Override
	public Customer updateCustomer(Customer customer, Customer customerNew) {
		customer.setMobileNumber(customerNew.getMobileNumber());
		customer.setMailID(customerNew.getMailID());
		if(validation.isNameValid(customer.getCustName())
				&&validation.isNumberValid(customer.getMobileNumber())) {
			return adminRepo.save(customer);
		}
		return null;
	}
	
	//to get a specific customer
	@Override
	public Customer getCustomer(long accno) {
		if(validation.isAccountValid(String.valueOf(accno)))
			return null;
		return adminRepo.findById(accno).orElseThrow(()->new RuntimeException("Customer not found for account number"+accno));
	}
	
	
	
	

}
