package com.nhattan.ecommerce.dto.request;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.nhattan.ecommerce.entity.UserEntity;

public class CreateUserRequest {
	@NotBlank(message = "cannot-be-empty")
	private String email;
	@NotBlank(message = "cannot-be-empty")
	@Length(min = 8, max = 30, message = "length-between-8-and-30")
	private String password;
	private String phoneNumber;
	@NotBlank(message = "cannot-be-empty")
	private String firstName;
	@NotBlank(message = "cannot-be-empty")
	private String lastName;
	private int gender;
	private Date dateOfBirth;

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public static UserEntity toEntity(CreateUserRequest dto) {
		UserEntity user = new UserEntity();
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setDateOfBirth(dto.getDateOfBirth());
		user.setEmail(dto.getEmail());
		user.setGender(dto.getGender());
		user.setPhoneNumber(dto.getPhoneNumber());
		return user;
	}
}
