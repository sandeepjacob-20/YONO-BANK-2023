package com.nissan.service;

import org.springframework.http.ResponseEntity;

import com.nissan.common.APIResponse;
import com.nissan.model.Customer;

public interface ICustomerService {
	//deposit money into account
	public Customer deposit(float amount, long accNo);
	
	//withdraw money from account
	public ResponseEntity<APIResponse> withdraw(float amount, long accNo);
	
	//check balance
	public float balance(long accNo);
	
	//transfer
	public ResponseEntity<APIResponse> transfer(long fromAcc, long toAcc, float amount);
}
