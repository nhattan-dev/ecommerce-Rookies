package com.nhattan.ecommerce.request;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class UpdateProductRequest {
	@Min(value = 0, message = "must-be-greater-than-or-equals-0")
	private int productID;
	@NotBlank(message = "cannot-be-empty")
	private String name;
	private String description;
	@Min(value = 0, message = "must-be-greater-than-or-equals-0")
	private int subcategoryID;
	@Min(value = 0, message = "must-be-greater-than-or-equals-0")
	private long price;
	@Min(value = 0, message = "must-be-greater-than-or-equals-0")
	private int quantity;

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSubcategoryID() {
		return subcategoryID;
	}

	public void setSubcategoryID(int subcategoryID) {
		this.subcategoryID = subcategoryID;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
