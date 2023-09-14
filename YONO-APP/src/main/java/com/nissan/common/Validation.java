package com.nissan.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Component;

@Component
public class Validation {
	//name validation
	public boolean isNameValid(String name) {
		boolean bool=false;
		try {
			Pattern namePattern = Pattern.compile("[^A-Za-z]");
			Matcher nameMatcher = namePattern.matcher(name);
			if(nameMatcher.find()) {
				throw new InvalidNameException("Hey! Invalid Name");
			}
			else
				bool= true;
		}catch(InvalidNameException e) {
			e.getMessage();
		}
		return bool;
	}
	
	public boolean isNumberValid(String phone) {
		boolean bool=false;
		try {
			Pattern phonePattern = Pattern.compile("[^0-9]");
			Matcher phoneMatcher = phonePattern.matcher(phone);
			if(phoneMatcher.find()|| phone.length()!=10) {
				throw new InvalidNameException("Hey! Invalid Number");
			}
			else
				bool= true;
		}catch(InvalidNameException e) {
			e.getMessage();
		}
		return bool;
	}
	public boolean isAccountValid(String accNo) {
		boolean bool=false;
		try {
			Pattern accPattern = Pattern.compile("[^0-9]");
			Matcher accMatcher = accPattern.matcher(accNo);
			if(accMatcher.find()|| accNo.length()!=6) {
				throw new InvalidNameException("Hey! Invalid Account Number");
			}
			else
				bool= true;
		}catch(InvalidNameException e) {
			e.getMessage();
		}
		return bool;
	}

}
