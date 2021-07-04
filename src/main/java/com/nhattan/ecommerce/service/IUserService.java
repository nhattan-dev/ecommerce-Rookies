package com.nhattan.ecommerce.service;

import java.util.List;

import com.nhattan.ecommerce.request.CreateUserRequest;
import com.nhattan.ecommerce.request.UpdateUserRequest;
import com.nhattan.ecommerce.response.CreateUserResponse;
import com.nhattan.ecommerce.response.ReadUserResponse;
import com.nhattan.ecommerce.response.UpdateUserResponse;

public interface IUserService {
	String login(String username, String password);
	CreateUserResponse register(CreateUserRequest userRequest);
	UpdateUserResponse update(UpdateUserRequest userRequest);
	List<ReadUserResponse> showAll();
	void delete(int userID);
	void changePassword(String email, String password);
}
