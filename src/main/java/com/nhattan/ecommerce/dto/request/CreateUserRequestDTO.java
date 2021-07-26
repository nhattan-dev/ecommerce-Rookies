package com.nhattan.ecommerce.dto.request;

import com.nhattan.ecommerce.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

@Getter
@Setter
public class CreateUserRequestDTO {
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
	private String gender;
	private Date dateOfBirth;

	public CreateUserRequestDTO() {
	}

	public CreateUserRequestDTO(String email, String password, String phoneNumber, String firstName, String lastName, String gender, Date dateOfBirth) {
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}

	public static UserEntity toEntity(CreateUserRequestDTO dto) {
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
