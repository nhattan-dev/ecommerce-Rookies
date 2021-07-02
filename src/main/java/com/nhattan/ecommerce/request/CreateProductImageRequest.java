package com.nhattan.ecommerce.request;

public class CreateProductImageRequest {
	private String imagePath;
	private int productID;

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
