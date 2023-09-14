package com.nissan.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.repo.ICustomerRepo;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepo customerRepo;
	
	//depositing money to account
	@Transactional
	@Override
	public void deposit(float amount,long accNo) {
		if(amount<50000)
			customerRepo.depositAmount(amount, accNo);
	}
	
	//withdrawing money from account
	@Transactional
	@Override
	public void withdraw(float amount, long accNo) {
		if(amount<50000) {
			float bal = customerRepo.getBalance(accNo);
			float minBal =  customerRepo.getMinBalance(accNo);
			if(bal-minBal>amount)
				customerRepo.withdraw(amount, accNo);	
		}
	}

	//checking account balance
	@Override
	public float balance(long accNo) {
		return customerRepo.getBalance(accNo);
	}

	//to transfer money to another account
	@Transactional
	@Override
	public void transfer(long fromAcc, long toAcc, float amount) {
		// TODO Auto-generated method stub
		float bal = customerRepo.getBalance(fromAcc);
		float minBal =  customerRepo.getMinBalance(fromAcc);
		if(bal-minBal>amount) {
			customerRepo.debitFrom(fromAcc, amount);
			customerRepo.creditTo(toAcc, amount);
		}
	}
	

}
