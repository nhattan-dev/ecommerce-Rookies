package com.nhattan.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "OrderDetail", uniqueConstraints = { @UniqueConstraint(columnNames = { "productID", "orderID" }) })
public class OrderDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderDetailID")
	private int orderDetailID = 0;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@Column(name = "color", nullable = false)
	private String color;

	@Column(name = "price", nullable = false)
	private int price;

	@Column(name = "size", nullable = false)
	private String size;

	@Column(name = "fulfilled")
	private int fulfilled = 1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productID", nullable = false)
	private ProductEntity product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderID", nullable = false)
	private OrderEntity order;

	public OrderDetailEntity() {
		super();
	}

	public OrderDetailEntity(int orderDetailID) {
		super();
		this.orderDetailID = orderDetailID;
	}

	public int getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(int orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getFulfilled() {
		return fulfilled;
	}

	public void setFulfilled(int fulfilled) {
		this.fulfilled = fulfilled;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}
}
