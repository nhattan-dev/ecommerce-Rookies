package com.nhattan.ecommerce.request;

public class CreateProductSizeRequest {
	private String size;
	private int productID;

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
