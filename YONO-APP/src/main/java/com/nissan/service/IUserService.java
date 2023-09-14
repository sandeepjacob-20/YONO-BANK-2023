package com.nissan.service;

import com.nissan.common.APIResponse;
import com.nissan.model.Users;

public interface IUserService {
	//add user
	public Users saveUser(Users user);
	
	//verify admin
	public APIResponse findAdminByNameAndPassword(String userName, String password);
	
	//verify Customer
	public APIResponse findCustomerByNameAndPassword(String userName, String password);
}
