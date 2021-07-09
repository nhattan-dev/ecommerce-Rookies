package com.nhattan.ecommerce.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerID")
	private int customerID = 0;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "customer")
	private List<AddressEntity> addresses = new ArrayList<AddressEntity>();

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "customer")
	private List<OrderEntity> orders = new ArrayList<OrderEntity>();

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "customer")
	private List<ProductRatingEntity> productRatings = new ArrayList<ProductRatingEntity>();

	public CustomerEntity() {
		super();
	}

	public CustomerEntity(int customerID) {
		super();
		this.customerID = customerID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	public List<ProductRatingEntity> getProductRatings() {
		return productRatings;
	}

	public void setProductRatings(List<ProductRatingEntity> productRatings) {
		this.productRatings = productRatings;
	}

}
