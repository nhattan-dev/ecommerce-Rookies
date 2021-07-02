package com.nhattan.ecommerce.request;

import org.hibernate.validator.constraints.NotBlank;

public class UpdateSubcategoryRequest {
	private int subcategoryID;
	@NotBlank(message = "cannot-be-empty")
	private String subcategoryName;
	private String description;
	private int categoryID;

	public int getSubcategoryID() {
		return subcategoryID;
	}

	public void setSubcategoryID(int subcategoryID) {
		this.subcategoryID = subcategoryID;
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
}
