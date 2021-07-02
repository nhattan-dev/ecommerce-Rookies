package com.nhattan.ecommerce.request;

public class CreateProductColorRequest {
	private String color;
	private int productID;

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
