package com.nhattan.ecommerce.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class ProductColorDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productColorID;
	@NotBlank(message = "cannot-be-empty")
	private String color;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productID;

	public int getProductColorID() {
		return productColorID;
	}

	public void setProductColorID(int productColorID) {
		this.productColorID = productColorID;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

}
