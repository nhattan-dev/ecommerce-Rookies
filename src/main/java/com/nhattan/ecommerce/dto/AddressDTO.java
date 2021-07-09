package com.nhattan.ecommerce.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class AddressDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int addressID;
	@NotBlank(message = "cannot-be-empty")
	private String fullname;
	@NotBlank(message = "cannot-be-empty")
	private String address;
	@NotBlank(message = "cannot-be-empty")
	private String phoneNumber;
	private CustomerDTO customer;

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
}
