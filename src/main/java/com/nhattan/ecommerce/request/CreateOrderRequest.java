package com.nhattan.ecommerce.request;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;

public class CreateOrderRequest {
	@Min(value = 0, message = "must-be-greater-than-or-equals-0")
	private int customerID;
	private Set<CreateOrderDetailRequest> orderDetails = new HashSet<CreateOrderDetailRequest>();

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public Set<CreateOrderDetailRequest> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<CreateOrderDetailRequest> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
