package com.nhattan.ecommerce.response;

import java.util.List;

public class CategoryResponse {
	private int categoryID;
	private String categoryCode = "";
	private String categoryName;
	private String description;
	private String imagePath;
	private List<SubcategoryResponse> subcategories;

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<SubcategoryResponse> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<SubcategoryResponse> subcategories) {
		this.subcategories = subcategories;
	}

}
