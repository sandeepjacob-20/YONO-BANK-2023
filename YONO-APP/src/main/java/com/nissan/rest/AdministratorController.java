package com.nissan.rest;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.common.APIResponse;
import com.nissan.model.Customer;
import com.nissan.service.AdministratorService;
import com.nissan.util.JwtUtil;


@RestController
@RequestMapping("/api")
public class AdministratorController {
	
	@Autowired
	private APIResponse apiResponse;
	
	@Autowired
	private AdministratorService adminService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	//Adding customer
	@PostMapping("/customer")
	public ResponseEntity<APIResponse> addEmployee(@RequestBody Customer customer,
			@RequestHeader(value="authorization",defaultValue="")String auth)throws AccessDeniedException{
		jwtUtil.verifyAdmin(auth);
		if(adminService.saveCustomer(customer)==null) {
				apiResponse.setData("Name can have only alphabets!!");
				apiResponse.setStatus(500);
				apiResponse.setEror("Invalid Name");
				
				return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		apiResponse.setData("Customer added successfully");
		apiResponse.setStatus(200);
		
		return ResponseEntity
				.status(apiResponse.getStatus()).body(apiResponse);
		
	}
	
	//listing customers
	@GetMapping("/customer")
	public List<Customer> getCustomers(@RequestHeader(value="authorization",defaultValue="")String auth)throws AccessDeniedException{
		jwtUtil.verifyAdmin(auth);
		return adminService.getCustomer();
	}
	
	
	//updating customer details
	@PutMapping("/customer/{accNo}")
	public ResponseEntity<APIResponse> updateCustomer(@PathVariable long accNo, @RequestBody Customer customer, 
			@RequestHeader(value="authorization",defaultValue="")String auth)throws AccessDeniedException {
		jwtUtil.verifyAdmin(auth);
		if(adminService.updateCustomer(adminService.getCustomer(accNo),customer)==null) {
			apiResponse.setData("Mobile Number is invalid !!!");
			apiResponse.setStatus(500);
			apiResponse.setEror("Invalid Number");
			
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		apiResponse.setData("Customer updated successfully");
		apiResponse.setStatus(200);
		
		return ResponseEntity
				.status(apiResponse.getStatus()).body(apiResponse);
			
	}
	
	//deleting a customer
	@GetMapping("/customer/{accno}")
	public ResponseEntity<APIResponse> deleteCustomer(@PathVariable long accno, @RequestHeader(value="authorization",defaultValue="")String auth)
			throws AccessDeniedException {
		jwtUtil.verifyAdmin(auth);
		if(adminService.deleteCustomer(accno)==null) {
			apiResponse.setData("Account Number is invalid !!!");
			apiResponse.setStatus(500);
			apiResponse.setEror("Invalid Account Number");
			
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		apiResponse.setData("Customer deleted successfully");
		apiResponse.setStatus(200);
		
		return ResponseEntity
				.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	//specific customer
	@GetMapping("/findcustomer/{accno}")
	public Customer getOneCustomer(@PathVariable long accno, @RequestHeader(value="authorization",defaultValue="")String auth)
			throws AccessDeniedException {
		jwtUtil.verifyAdmin(auth);
		if(adminService.getCustomer(accno)!=null)
			return adminService.getCustomer(accno);
		else
			throw new RuntimeException("Customer not found for account number"+accno);
	}
	
	
	
}
