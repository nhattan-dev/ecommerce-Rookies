package com.nhattan.ecommerce.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class ProductSizeDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productSizeID;
	@NotBlank(message = "cannot-be-empty")
	private String size;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productID;

	public int getProductSizeID() {
		return productSizeID;
	}

	public void setProductSizeID(int productSizeID) {
		this.productSizeID = productSizeID;
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
