package com.nissan.service;

public interface ICustomerService {
	//deposit money into account
	public void deposit(float amount, long accNo);
	
	//withdraw money from account
	public void withdraw(float amount, long accNo);
	
	//check balance
	public float balance(long accNo);
	
	//transfer
	public void transfer(long fromAcc, long toAcc, float amount);
}
