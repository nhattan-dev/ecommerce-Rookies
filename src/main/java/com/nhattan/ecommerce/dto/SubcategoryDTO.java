package com.nhattan.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class SubcategoryDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int subcategoryID = 0;
	private String subcategoryCode;
	@NotBlank(message = "cannot-be-empty")
	private String subcategoryName;
	private String description;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int categoryID;
	private List<ProductDTO> products = new ArrayList<ProductDTO>();

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

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

}
