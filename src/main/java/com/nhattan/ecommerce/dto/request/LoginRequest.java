package com.nhattan.ecommerce.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
	@NotBlank(message = "cannot-be-null")
	private String email;
	@NotBlank(message = "cannot-be-null")
	@Length(min = 8, max = 30, message = "password-length-must-be-between-8-and-30")
	private String password;
}
