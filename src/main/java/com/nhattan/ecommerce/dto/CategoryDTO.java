package com.nhattan.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.nhattan.ecommerce.entity.CategoryEntity;

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

	public CategoryDTO() {
		super();
	}

	public CategoryDTO(int categoryID, String categoryCode, String categoryName, String description, String imagePath) {
		super();
		this.categoryID = categoryID;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.description = description;
		this.imagePath = imagePath;
	}

	public CategoryDTO(String categoryName, String description, String imagePath) {
		super();
		this.categoryName = categoryName;
		this.description = description;
		this.imagePath = imagePath;
	}

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

	public static CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity category = new CategoryEntity();
		category.setCategoryID(dto.getCategoryID());
		category.setCategoryName(dto.getCategoryName());
		category.setDescription(dto.getDescription());
		category.setImagePath(dto.getImagePath());
		return category;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryDTO other = (CategoryDTO) obj;
		if (categoryCode == null) {
			if (other.categoryCode != null)
				return false;
		} else if (!categoryCode.equals(other.categoryCode))
			return false;
		if (categoryID != other.categoryID)
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoryDTO [categoryID=" + categoryID + ", categoryCode=" + categoryCode + ", categoryName="
				+ categoryName + ", description=" + description + ", imagePath=" + imagePath + "]";
	}
}
