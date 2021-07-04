package com.nhattan.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

public class CustomerDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int customerID;
	private List<AddressDTO> addresses = new ArrayList<AddressDTO>();
	private List<OrderDTO> orders = new ArrayList<OrderDTO>();

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public List<AddressDTO> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}

	public List<OrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDTO> orders) {
		this.orders = orders;
	}
}
