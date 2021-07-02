package com.nhattan.ecommerce.response;

import java.util.Date;
import java.util.List;

public class ReadOrderResponse {
	private int orderID;
	private String orderCode;
	private int transactStatus;
	private int paid;
	private Date orderDate;
	private int customerID;
	private List<CreateOrderDetailResponse> orderDetails;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public int getTransactStatus() {
		return transactStatus;
	}

	public void setTransactStatus(int transactStatus) {
		this.transactStatus = transactStatus;
	}

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public List<CreateOrderDetailResponse> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<CreateOrderDetailResponse> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
