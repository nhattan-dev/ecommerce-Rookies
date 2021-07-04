package com.nhattan.ecommerce.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class ProductImageDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productImageID;
	@NotBlank(message = "cannot-be-empty")
	private String imagePath;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productID;

	public int getProductImageID() {
		return productImageID;
	}

	public void setProductImageID(int productImageID) {
		this.productImageID = productImageID;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

}
