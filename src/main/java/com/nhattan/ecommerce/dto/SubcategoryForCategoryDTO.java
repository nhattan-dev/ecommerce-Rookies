package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.CategoryEntity;
import com.nhattan.ecommerce.entity.SubcategoryEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

public class SubcategoryForCategoryDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int subcategoryID = 0;
	private String subcategoryCode;
	@NotBlank(message = "cannot-be-empty")
	private String subcategoryName;
	private String description;

	@Min(message = "must-be-greater-than-or-equals-0", value = 0)

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

	public static SubcategoryEntity toEntity(SubcategoryDTO dto) {
		SubcategoryEntity subcategory = new SubcategoryEntity();
		subcategory.setSubcategoryID(dto.getSubcategoryID());
		subcategory.setSubcategoryName(dto.getSubcategoryName());
		subcategory.setDescription(dto.getDescription());
		CategoryEntity cate = new CategoryEntity();
		cate.setCategoryID(dto.getCategoryID());
		subcategory.setCategory(cate);
		return subcategory;
	}
}
