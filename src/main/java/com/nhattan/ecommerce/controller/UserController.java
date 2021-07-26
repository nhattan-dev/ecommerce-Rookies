package com.nhattan.ecommerce.controller;

import com.nhattan.ecommerce.dto.UserDTO;
import com.nhattan.ecommerce.dto.request.CreateUserRequestDTO;
import com.nhattan.ecommerce.dto.request.LoginRequest;
import com.nhattan.ecommerce.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

	private final IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/auth/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginRequest login) {
		return ResponseEntity.ok().body(userService.login(login));
	}

	@GetMapping(value = "/admin/user")
	public ResponseEntity<List<UserDTO>> showAllUser() {
		return ResponseEntity.ok().body(userService.showAll());
	}

	@PostMapping(value = "/auth/register")
	public ResponseEntity<UserDTO> register(@Valid @RequestBody CreateUserRequestDTO userRequest) {
		return ResponseEntity.ok().body(userService.register(userRequest));
	}

	@GetMapping(value = "/account/verify")
	public ResponseEntity verify(@RequestParam("token") String token){
		return ResponseEntity.ok().body(userService.verify(token));
	}

	@PatchMapping(value = "/account/password")
	public ResponseEntity changePassword(@Valid @RequestBody LoginRequest login,
			@RequestHeader(name = "Authorization") String token) {
		userService.changePassword(login, removePrefix(token));
		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "/account")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO dto, @RequestHeader(name = "Authorization") String token) {
		return ResponseEntity.ok().body(userService.updateUser(dto, removePrefix(token)));
	}

	@DeleteMapping(value = "/account")
	public ResponseEntity invalidateUser(@RequestHeader(name = "Authorization") String token) {
		token = removePrefix(token);
		userService.invalidateUser(token);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/admin/user")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserRequestDTO dto){
		return ResponseEntity.ok().body(userService.createUser(dto));
	}

	@DeleteMapping(value = "/owner/user/{userID}")
	public ResponseEntity invalidateUser(@RequestHeader(name = "Authorization") String token, @PathVariable("userID") int userID) {
		token = removePrefix(token);
		userService.invalidateUserByUserID(token, userID);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/account")
	public ResponseEntity<UserDTO> showUser(@RequestHeader(name = "Authorization") String token) {
		token = removePrefix(token);
		return ResponseEntity.ok().body(userService.showOneUser(token));
	}

	private String removePrefix(String header) {
		return header.substring(7);
	}
}
