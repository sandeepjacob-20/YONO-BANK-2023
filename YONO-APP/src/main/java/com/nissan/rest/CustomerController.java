package com.nissan.rest;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.common.APIResponse;
import com.nissan.service.CustomerService;
import com.nissan.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class CustomerController {
	@Autowired
	private APIResponse apiResponse;
	
	@Autowired
	private CustomerService custService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	//to deposit money
	@GetMapping("/deposit/{amount}&{accNo}")
	public ResponseEntity<APIResponse> depositMoney(@PathVariable float amount, @PathVariable long accNo,
			@RequestHeader(value="authorization",defaultValue="")String auth)
			throws AccessDeniedException { 
		jwtUtil.verifyCustomer(auth);
		if(custService.deposit(amount,accNo)==null) {
			apiResponse.setData("Account Number is invalid or PAN Number Required!!!");
			apiResponse.setStatus(500);
			apiResponse.setEror("Invalid Account Number or PAN Required");
			
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		apiResponse.setData("Amount Deposited successfully");
		apiResponse.setStatus(200);
		
		return ResponseEntity
				.status(apiResponse.getStatus()).body(apiResponse);
			
	}
	
	//to withdraw money
	@GetMapping("/withdraw/{amount}&{accNo}")
	public ResponseEntity<APIResponse> withdrawMoney(@PathVariable float amount, @PathVariable long accNo,
			@RequestHeader(value="authorization",defaultValue="")String auth)
			throws AccessDeniedException  {
		jwtUtil.verifyCustomer(auth);
		return custService.withdraw(amount, accNo);
	}
	
	//to check balance
	@GetMapping("/balance/{accNo}")
	public float getBalance(@PathVariable long accNo, 
			@RequestHeader(value="authorization",defaultValue="")String auth)throws AccessDeniedException   {
		jwtUtil.verifyCustomer(auth);
		return custService.balance(accNo);
	}
	
	//transfer
	@GetMapping("/transfer/{fromAcc}&{toAcc}&{amount}")
	public void transfer(@PathVariable long fromAcc, @PathVariable long toAcc, @PathVariable float amount,
			@RequestHeader(value="authorization",defaultValue="")String auth)throws AccessDeniedException  {
		jwtUtil.verifyCustomer(auth);
		custService.transfer(fromAcc, toAcc, amount); 
	}
	
	
}
