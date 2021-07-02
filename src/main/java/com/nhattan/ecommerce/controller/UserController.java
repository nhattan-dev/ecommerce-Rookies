package com.nhattan.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhattan.ecommerce.request.CreateUserRequest;
import com.nhattan.ecommerce.response.CreateUserResponse;
import com.nhattan.ecommerce.response.ReadUserResponse;
import com.nhattan.ecommerce.service.IUserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping(value = "/auth/login")
	public String login(@Valid @RequestParam(name = "email") String email,
			@Valid @RequestParam(name = "password") String password) {
		return userService.login(email, password);
	}

	@GetMapping(value = "/user")
	public List<ReadUserResponse> showAllUser() {
		return userService.showAll();
	}

	@PostMapping(value = "/auth/register")
	public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest userRequest) {
		return userService.register(userRequest);
	}

//	@PutMapping(value = "/user")
//	public UpdateCustomerResponse updateUser(@Valid @RequestBody UpdateCustomerRequest Request) {
////		return userService.update(customerRequest);
//		return null;
//	}
}
