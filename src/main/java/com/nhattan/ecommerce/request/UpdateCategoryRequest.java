package com.nhattan.ecommerce.request;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateCategoryRequest {
	private int categoryID;
	@NotBlank(message = "cannot-be-empty")
	private String categoryName;
	@NotBlank(message = "cannot-be-empty")
	private String description;
	@NotBlank(message = "cannot-be-empty")
	private String imagePath;
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
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
}
