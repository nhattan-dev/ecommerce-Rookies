package com.nhattan.ecommerce.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderID")
	private int orderID = 0;

	@Column(name = "orderCode", unique = true, nullable = false)
	private String orderCode = "";

	@Column(name = "transactStatus")
	private int transactStatus = 1;

	@Column(name = "paid")
	private int paid = 1;

	@Column(name = "orderDate", nullable = false)
	private Date orderDate = new Date();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerID")
	private CustomerEntity customer;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "order", cascade = CascadeType.REMOVE)
	private List<OrderDetailEntity> orderDetails = new ArrayList<OrderDetailEntity>();

	public OrderEntity() {
		super();
	}

	public OrderEntity(int orderID) {
		super();
		this.orderID = orderID;
	}

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

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public List<OrderDetailEntity> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailEntity> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
