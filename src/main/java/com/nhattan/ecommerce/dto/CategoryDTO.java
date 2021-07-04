package com.nhattan.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class CategoryDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int categoryID = 0;
	private String categoryCode;
	@NotBlank(message = "cannot-be-empty")
	private String categoryName;
	private String description;
	@NotBlank(message = "cannot-be-empty")
	private String imagePath;
	private List<SubcategoryForCategoryDTO> subcategories = new ArrayList<SubcategoryForCategoryDTO>();

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

	public List<SubcategoryForCategoryDTO> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<SubcategoryForCategoryDTO> subcategories) {
		this.subcategories = subcategories;
	}
}
