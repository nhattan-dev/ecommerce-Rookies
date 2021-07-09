package com.nhattan.ecommerce.dto;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.nhattan.ecommerce.entity.ProductEntity;
import com.nhattan.ecommerce.entity.SubcategoryEntity;

class ProductDTOTest {

	@Test
	void testToEntity() {
		// Give
		ProductDTO dto = new ProductDTO(5, "Name", "description", 300, 50000, 3);
		ProductEntity entity = new ProductEntity(5, "Name", "description", 300, new SubcategoryEntity(3));
		// Then
		assertTrue(ProductDTO.toEntity(dto).equals(entity));
	}

	@Test
	void testToDTO() {
		// Give
		ProductDTO dto = new ProductDTO(5, "Name", "description", 0, 1, 300, 3);
		ProductEntity entity = new ProductEntity(5, "Name", "description", 0, 1, 300, new SubcategoryEntity(3));
		// Then
		System.out.println(ProductDTO.toDTO(entity).toString());
		System.out.println(dto.toString());
		assertTrue(ProductDTO.toDTO(entity).equals(dto));
	}

}
