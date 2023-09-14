package com.nissan.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.common.APIResponse;
import com.nissan.common.Validation;
import com.nissan.model.Users;
import com.nissan.repo.IUserRepository;
import com.nissan.util.JwtUtil;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private Validation validation;
	
	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private APIResponse apiResponse;
	
	
	@Override
	public APIResponse findAdminByNameAndPassword(String userName, String password) {
		//verify user exist or not
		Users user = userRepository.findAdminByUserNameAndPassword(userName, password);
		
		if(user==null) {
			apiResponse.setData("INVALID CREDENTIALS");
			return apiResponse;
		}
		
		//credentials are correct then
		String token = jwtutil.generateJwtAdmin(user);
		
		//storing more details and tokens
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("ACCESS TOKEN", token);
		data.put("role", user.getRoleId());
		data.put("UserName",user.getUserName());
		
		apiResponse.setStatus(200);
		apiResponse.setData(data);
		
		return apiResponse;
		
	}
	
	@Override
	public APIResponse findCustomerByNameAndPassword(String userName, String password) {
		//verify user exist or not
		Users user = userRepository.findCustomerByUserNameAndPassword(userName, password);
		
		if(user==null) {
			apiResponse.setData("INVALID CREDENTIALS");
			return apiResponse;
		}
		
		//credentials are correct then
		String token = jwtutil.generateJwtCustomer(user);
		
		//storing more details and tokens
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("ACCESS TOKEN", token);
		data.put("role", user.getRoleId());
		data.put("UserName",user.getUserName());
		
		apiResponse.setStatus(200);
		apiResponse.setData(data);
		
		return apiResponse;
		
	}
 
	//save users
	@Override
	public Users saveUser(Users user) {
		if(validation.isNameValid(user.getUserName())) {
			return userRepository.save(user);
		}
		return null;
	}

}
