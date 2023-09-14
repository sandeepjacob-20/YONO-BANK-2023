package com.nissan.service;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Generator {

	// to generate the account number
	public String getAccountNo() {
		Random rand = new Random();
		String accountNumber = "";
		for (int i = 0; i < 9; i++) {
			int digit = rand.nextInt(9);
			accountNumber += digit;
		}
		return accountNumber;
	}

	//to generate the pin
	public String getPin() {
		Random rand = new Random();
		String pinNumber = "";
		for (int i = 0; i < 4; i++) {
			int digit = rand.nextInt(9);
			pinNumber += digit;
		}
		return pinNumber;
	}
}
