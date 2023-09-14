package com.nissan.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nissan.common.APIResponse;
import com.nissan.common.Validation;
import com.nissan.model.Customer;
import com.nissan.repo.ICustomerRepo;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepo customerRepo;
	
	@Autowired
	private Validation validation;
	
	@Autowired
	private APIResponse apiResponse;
	//depositing money to account
	@Transactional
	@Override
	public Customer deposit(float amount,long accNo) {
		if(validation.isAccountValid(String.valueOf(accNo))) {
			if(amount<50000)
				return customerRepo.depositAmount(amount, accNo);
			else
				return null;
		}
		return null;
	}
	
	//withdrawing money from account
	@Transactional
	@Override
	public ResponseEntity<APIResponse> withdraw(float amount, long accNo) {
		if(validation.isAccountValid(String.valueOf(accNo))) {
			if(amount<50000) {
				float bal = customerRepo.getBalance(accNo);
				float minBal =  customerRepo.getMinBalance(accNo);
				if(bal-minBal>amount) {
					customerRepo.withdraw(amount, accNo);
				}
				else {
					apiResponse.setData("Insufficient Funds!!!");
					apiResponse.setStatus(500);
					apiResponse.setEror("Insufficient Funds");
					
					return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
				}
				apiResponse.setData("Amount Withdrawn successfully");
				apiResponse.setStatus(200);
				
				return ResponseEntity
						.status(apiResponse.getStatus()).body(apiResponse);
			}
			apiResponse.setData("PAN Number Required!!!");
			apiResponse.setStatus(500);
			apiResponse.setEror("PAN Number");
			
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		apiResponse.setData("Invalid Account Number !!!");
		apiResponse.setStatus(500);
		apiResponse.setEror("Account Number Invalid");
		
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	//checking account balance
	@Override
	public float balance(long accNo) {
		if(validation.isAccountValid(String.valueOf(accNo)))
			return customerRepo.getBalance(accNo);
		else
			return -1;
	}

	//to transfer money to another account
	@Transactional
	@Override
	public ResponseEntity<APIResponse> transfer(long fromAcc, long toAcc, float amount) {
		// TODO Auto-generated method stub
		if(validation.isAccountValid(String.valueOf(fromAcc))
				&&validation.isAccountValid(String.valueOf(toAcc))) {
			float bal = customerRepo.getBalance(fromAcc);
			float minBal =  customerRepo.getMinBalance(fromAcc);
			if(bal-minBal>amount) {
				customerRepo.debitFrom(fromAcc, amount);
				customerRepo.creditTo(toAcc, amount);
			}
			else {
				apiResponse.setData("Insuffiecient Balance !!!");
				apiResponse.setStatus(500);
				apiResponse.setEror("Insuffiecient Balance");
				
				return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
			}
			apiResponse.setData("Amount Transfered Successfully !!!");
			apiResponse.setStatus(200);
			
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		else {
			apiResponse.setData("Invalid Account Number !!!");
			apiResponse.setStatus(500);
			apiResponse.setEror("Account Number Invalid");
			
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
			
	}
	

}
