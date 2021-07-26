package com.nhattan.ecommerce.service;

import com.nhattan.ecommerce.dto.UserDTO;
import com.nhattan.ecommerce.dto.request.CreateUserRequestDTO;
import com.nhattan.ecommerce.dto.request.LoginRequest;

import java.util.List;

public interface IUserService {
	String login(LoginRequest login);

	UserDTO register(CreateUserRequestDTO userRequest);

	String verify(String token);

	UserDTO createUser(CreateUserRequestDTO userRequest);

	UserDTO updateUser(UserDTO userRequest, String token);

	List<UserDTO> showAll();

	UserDTO showOneUser(String token);

	void invalidateUser(String token);

	void invalidateUserByUserID(String token, int userID);

	void changePassword(LoginRequest login, String token);
}
