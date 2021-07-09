package com.nhattan.ecommerce.dto;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.nhattan.ecommerce.entity.CategoryEntity;

class CategoryDTOTest {

	@Test
	void testToEntity() {
		//Give
		CategoryDTO dto = new CategoryDTO("đồng Hồ hiệu","không mô tả","imagePath");
		CategoryEntity entity = new CategoryEntity(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả", "imagePath", 1);
	
		//Then
		assertTrue(CategoryDTO.toEntity(dto).equals(entity));
	}

}
