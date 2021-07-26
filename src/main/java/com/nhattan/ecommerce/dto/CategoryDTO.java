package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.CategoryEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CategoryDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int categoryID = 0;
	private String categoryCode;
	@NotBlank(message = "cannot-be-empty")
	private String categoryName;
	private String description;
	@NotBlank(message = "cannot-be-empty")
	private String imagePath;
	private String status;
	private List<SubcategoryDTO> subcategories = new ArrayList<>();

	public CategoryDTO() {
	}

	public CategoryDTO(int categoryID, String categoryCode, String categoryName, String description, String imagePath, String status) {
		this.categoryID = categoryID;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.description = description;
		this.imagePath = imagePath;
		this.status = status;
	}

	public static CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity category = new CategoryEntity();
		category.setCategoryID(dto.getCategoryID());
		category.setCategoryName(dto.getCategoryName());
		category.setDescription(dto.getDescription());
		category.setImagePath(dto.getImagePath());
		return category;
	}

	public static CategoryDTO toDTO(CategoryEntity entity){
		CategoryDTO dto = new CategoryDTO();
		dto.setCategoryID(entity.getCategoryID());
		dto.setCategoryCode(entity.getCategoryCode());
		dto.setCategoryName(entity.getCategoryName());
		dto.setDescription(entity.getDescription());
		dto.setImagePath(entity.getImagePath());
		dto.setStatus(entity.getStatus());
		return dto;
	}
}
