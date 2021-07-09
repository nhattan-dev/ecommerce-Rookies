package com.nhattan.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhattan.ecommerce.dto.UserDTO;
import com.nhattan.ecommerce.dto.request.CreateUserRequest;
import com.nhattan.ecommerce.dto.request.LoginRequest;
import com.nhattan.ecommerce.service.IUserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserService userService;

	@PostMapping(value = "/auth/login")
	public String login(@Valid @RequestBody LoginRequest login) {
		return userService.login(login);
	}

	@GetMapping(value = "/admin/user")
	public List<UserDTO> showAllUser() {
		return userService.showAll();
	}

	@PostMapping(value = "/auth/register")
	public UserDTO createUser(@Valid @RequestBody CreateUserRequest userRequest) {
		return userService.register(userRequest);
	}

	@PatchMapping(value = "/user/changepassword")
	public void changePassword(@Valid @RequestBody LoginRequest login,
			@RequestHeader(name = "Authorization") String token) {
		token = getToken(token);
		userService.changePassword(login, token);
	}

	@PutMapping(value = "/user")
	public void updateUser(@Valid @RequestBody UserDTO dto, @RequestHeader(name = "Authorization") String token) {
		token = getToken(token);
		userService.updateUser(dto, token);
	}

	@DeleteMapping(value = "/user")
	public void deleteUser(@RequestHeader(name = "Authorization") String token) {
		token = getToken(token);
		userService.invalidateUser(token);
	}

	@GetMapping(value = "/user")
	public void showUser(@RequestHeader(name = "Authorization") String token) {
		token = getToken(token);
		userService.showOneUser(token);
	}

	private String getToken(String header) {
		return header.substring(7, header.length());
	}
}
