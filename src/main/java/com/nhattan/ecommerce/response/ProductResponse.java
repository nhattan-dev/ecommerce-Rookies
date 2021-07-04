package com.nhattan.ecommerce.response;

import java.util.List;

public class ProductResponse {
	private int productID;
	private String name;
	private String description;
	private float point;
	private int quantity;
	private List<ProductImageResponse> productImages;
	private List<ProductColorResponse> productColors;
	private List<ProductSizeResponse> productSizes;

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

	public float getPoint() {
		return point;
	}

	public void setPoint(float point) {
		this.point = point;
	}

	public List<ProductImageResponse> getProductImages() {
		return productImages;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setProductImages(List<ProductImageResponse> productImages) {
		this.productImages = productImages;
	}

	public List<ProductColorResponse> getProductColors() {
		return productColors;
	}

	public void setProductColors(List<ProductColorResponse> productColors) {
		this.productColors = productColors;
	}

	public List<ProductSizeResponse> getProductSizes() {
		return productSizes;
	}

	public void setProductSizes(List<ProductSizeResponse> productSizes) {
		this.productSizes = productSizes;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	private long price;
}
