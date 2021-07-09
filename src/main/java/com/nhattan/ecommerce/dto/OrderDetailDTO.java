package com.nhattan.ecommerce.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class OrderDetailDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int orderDetailID;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int quantity;
	@NotBlank(message = "cannot-be-empty")
	private String color;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int price;
	@NotBlank(message = "cannot-be-empty")
	private String size;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productID;

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

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
}
