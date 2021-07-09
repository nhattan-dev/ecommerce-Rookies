package com.nhattan.ecommerce.dto.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class LoginRequest {
	@NotBlank(message = "cannot-be-null")
	private String email;
	@NotBlank(message = "cannot-be-null")
	@Length(min = 8, max = 30, message = "password-length-must-be-between-8-and-30")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
