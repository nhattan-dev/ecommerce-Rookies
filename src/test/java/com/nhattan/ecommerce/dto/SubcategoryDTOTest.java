package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.dto.SubcategoryDTO;
import com.nhattan.ecommerce.entity.CategoryEntity;
import com.nhattan.ecommerce.entity.SubcategoryEntity;
import com.nhattan.ecommerce.enums.STATUS;
import org.junit.jupiter.api.Test;
//import org.junit.Test;

import static org.junit.Assert.assertTrue;

class SubcategoryDTOTest {

	@Test
	void testToEntity() {
		// Give
		SubcategoryDTO dto = new SubcategoryDTO();
		dto.setSubcategoryID(5);
		dto.setSubcategoryName("SubcategoryName");
		dto.setDescription("Description");
		dto.setCategoryID(3);

		SubcategoryEntity entity = new SubcategoryEntity();
		entity.setSubcategoryID(5);
		entity.setSubcategoryName("SubcategoryName");
		entity.setDescription("Description");
		CategoryEntity cate = new CategoryEntity();
		cate.setCategoryID(3);
		entity.setCategory(cate);


		SubcategoryEntity result = SubcategoryDTO.toEntity(dto);

		assertTrue(result.getDescription() == entity.getDescription());
		assertTrue(result.getSubcategoryName() == entity.getSubcategoryName());
		assertTrue(result.getSubcategoryID() == entity.getSubcategoryID());
		assertTrue(result.getCategory().getCategoryID() == entity.getCategory().getCategoryID());
	}

	@Test
	void testToDTO() {
		// Give
		SubcategoryEntity entity = new SubcategoryEntity();
		entity.setSubcategoryID(5);
		entity.setSubcategoryName("SubcategoryName");
		entity.setSubcategoryCode("subcategoryname");
		entity.setDescription("Description");
		entity.setStatus(STATUS.NOT_AVAILABLE.name());
		CategoryEntity cate = new CategoryEntity();
		cate.setCategoryID(3);
		entity.setCategory(cate);

		SubcategoryDTO dto = new SubcategoryDTO();
		dto.setSubcategoryID(5);
		dto.setSubcategoryName("SubcategoryName");
		dto.setSubcategoryCode("subcategoryname");
		dto.setDescription("Description");
		dto.setStatus(STATUS.NOT_AVAILABLE.name());
		dto.setCategoryID(3);

		SubcategoryDTO result = SubcategoryDTO.toDTO(entity);

		assertTrue(result.getDescription() == dto.getDescription());
		assertTrue(result.getSubcategoryCode() == dto.getSubcategoryCode());
		assertTrue(result.getSubcategoryName() == dto.getSubcategoryName());
		assertTrue(result.getStatus() == dto.getStatus());
		assertTrue(result.getCategoryID() == dto.getCategoryID());
		assertTrue(result.getSubcategoryID() == dto.getSubcategoryID());
	}

}
