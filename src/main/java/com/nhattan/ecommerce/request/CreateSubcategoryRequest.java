package com.nhattan.ecommerce.request;

import org.hibernate.validator.constraints.NotBlank;

public class CreateSubcategoryRequest {
	@NotBlank
	private String subcategoryName;
	private String description;
	private int categoryID;

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
}
