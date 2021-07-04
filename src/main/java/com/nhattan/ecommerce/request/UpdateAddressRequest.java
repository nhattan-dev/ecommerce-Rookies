package com.nhattan.ecommerce.request;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class UpdateAddressRequest {
	@Min(value = 0, message = "must-be-greater-than-or-equals-0")
	private int addressID;
	@NotBlank(message = "cannot-be-empty")
	private String fullname;
	@NotBlank(message = "cannot-be-empty")
	private String address;

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
