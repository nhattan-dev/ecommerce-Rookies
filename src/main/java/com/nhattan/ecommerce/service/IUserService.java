package com.nhattan.ecommerce.service;

import java.util.List;

import com.nhattan.ecommerce.dto.UserDTO;
import com.nhattan.ecommerce.dto.request.CreateUserRequest;
import com.nhattan.ecommerce.dto.request.LoginRequest;

public interface IUserService {
	String login(LoginRequest login);

	UserDTO register(CreateUserRequest userRequest);

	UserDTO updateUser(UserDTO userRequest, String token);

	List<UserDTO> showAll();

	UserDTO showOneUser(String token);

	void invalidateUser(String token);

	void changePassword(LoginRequest login, String token);
}
