package com.nhattan.ecommerce.request;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateCategoryRequest {
	@NotBlank(message = "cannot-be-empty")
	private String categoryName;
	@NotBlank(message = "cannot-be-empty")
	private String description;
	@NotBlank(message = "cannot-be-empty")
	private String imagePath;

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
