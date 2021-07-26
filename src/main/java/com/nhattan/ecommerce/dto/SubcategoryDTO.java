package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.CategoryEntity;
import com.nhattan.ecommerce.entity.SubcategoryEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubcategoryDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int subcategoryID = 0;
	private String subcategoryCode;
	@NotBlank(message = "cannot-be-empty")
	private String subcategoryName;
	private String description;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int categoryID;
	private String status;
	private List<ProductDTO> products = new ArrayList<ProductDTO>();

	public SubcategoryDTO() {
		super();
	}

	public SubcategoryDTO(String subcategoryName, String description, int categoryID) {
		super();
		this.subcategoryName = subcategoryName;
		this.description = description;
		this.categoryID = categoryID;
	}

	public SubcategoryDTO(int subcategoryID, String subcategoryName, String description, int categoryID) {
		super();
		this.subcategoryID = subcategoryID;
		this.subcategoryName = subcategoryName;
		this.description = description;
		this.categoryID = categoryID;
	}

	public SubcategoryDTO(int subcategoryID, String subcategoryCode, String subcategoryName, String description, int categoryID, String status) {
		this.subcategoryID = subcategoryID;
		this.subcategoryCode = subcategoryCode;
		this.subcategoryName = subcategoryName;
		this.description = description;
		this.categoryID = categoryID;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public static SubcategoryDTO toDTO(SubcategoryEntity subcategory) {
		SubcategoryDTO dto = new SubcategoryDTO();
		dto.setCategoryID(subcategory.getCategory().getCategoryID());
		dto.setDescription(subcategory.getDescription());
		dto.setSubcategoryCode(subcategory.getSubcategoryCode());
		dto.setSubcategoryID(subcategory.getSubcategoryID());
		dto.setSubcategoryName(subcategory.getSubcategoryName());
		dto.setStatus(subcategory.getStatus());
//		dto.setProducts(subcategory.getProducts().stream().map(x -> ProductDTO.toDTO(x)).collect(Collectors.toList()));
		return dto;
	}

	public static SubcategoryEntity toEntity(SubcategoryDTO dto){
		SubcategoryEntity entity = new SubcategoryEntity();
		entity.setSubcategoryID(dto.getSubcategoryID());
		entity.setSubcategoryName(dto.getSubcategoryName());
		entity.setDescription(dto.getDescription());
		CategoryEntity cate = new CategoryEntity();
		cate.setCategoryID(dto.getCategoryID());
		entity.setCategory(cate);
		return entity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubcategoryDTO other = (SubcategoryDTO) obj;
		if (categoryID != other.categoryID)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (subcategoryCode == null) {
			if (other.subcategoryCode != null)
				return false;
		} else if (!subcategoryCode.equals(other.subcategoryCode))
			return false;
		if (subcategoryID != other.subcategoryID)
			return false;
		if (subcategoryName == null) {
			if (other.subcategoryName != null)
				return false;
		} else if (!subcategoryName.equals(other.subcategoryName))
			return false;
		return true;
	}
}
