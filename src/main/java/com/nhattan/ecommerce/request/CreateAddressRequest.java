package com.nhattan.ecommerce.request;

import org.hibernate.validator.constraints.NotBlank;

public class CreateAddressRequest {
	@NotBlank(message = "cannot-be-empty")
	private String fullname;
	@NotBlank(message = "cannot-be-empty")
	private String address;
	private int customerID;

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

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
}
