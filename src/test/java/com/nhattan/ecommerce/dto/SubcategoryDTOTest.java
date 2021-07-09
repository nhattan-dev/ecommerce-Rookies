package com.nhattan.ecommerce.dto;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.nhattan.ecommerce.entity.CategoryEntity;
import com.nhattan.ecommerce.entity.SubcategoryEntity;

class SubcategoryDTOTest {

	@Test
	void testToEntity() {
		// Give
		SubcategoryDTO dto = new SubcategoryDTO(5, "đồng Hồ hiệu", "không mô tả", 3);
		SubcategoryEntity entity = new SubcategoryEntity(5, "đồng Hồ hiệu", "không mô tả", new CategoryEntity(3));
		SubcategoryEntity result = SubcategoryDTO.toEntity(dto);

		// Then
		assertTrue(result.equals(entity));
	}

	@Test
	void testToDTO() {
		// Give
		SubcategoryEntity entity = new SubcategoryEntity(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả", 1,
				new CategoryEntity(3));
		SubcategoryDTO assertResult = new SubcategoryDTO(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả", 3);

		// Then
		assertTrue(SubcategoryDTO.toDTO(entity).equals(assertResult));
	}

}
