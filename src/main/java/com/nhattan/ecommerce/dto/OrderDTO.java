package com.nhattan.ecommerce.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;

public class OrderDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int orderID;
	private String orderCode;
	private int transactStatus;
	private int paid;
	private Date orderDate = new Date();
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int customerID;
	private List<OrderDetailDTO> orderDetails = new ArrayList<OrderDetailDTO>();

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

	public List<OrderDetailDTO> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
