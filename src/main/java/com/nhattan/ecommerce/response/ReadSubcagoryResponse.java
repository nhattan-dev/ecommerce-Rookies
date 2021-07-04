package com.nhattan.ecommerce.response;

import java.util.List;

public class ReadSubcagoryResponse {
	private int subcategoryID;
	private String subcategoryCode;
	private String subcategoryName;
	private String description;
	private int categoryID;
	private List<ProductResponse> products;

	public int getSubcategoryID() {
		return subcategoryID;
	}

	public void setSubcategoryID(int subcategoryID) {
		this.subcategoryID = subcategoryID;
	}

	public String getSubcategoryCode() {
		return subcategoryCode;
	}

	public void setSubcategoryCode(String subcategoryCode) {
		this.subcategoryCode = subcategoryCode;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public List<ProductResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}
}
