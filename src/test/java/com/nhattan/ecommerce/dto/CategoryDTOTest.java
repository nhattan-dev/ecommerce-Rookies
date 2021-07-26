package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.dto.CategoryDTO;
import com.nhattan.ecommerce.entity.CategoryEntity;
import com.nhattan.ecommerce.enums.ORDER_STATUS;
import com.nhattan.ecommerce.enums.STATUS;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
//import org.junit.Test;

import static org.junit.Assert.assertTrue;

class CategoryDTOTest {
    CategoryDTO dto, assertDTO;
    CategoryEntity entity;

    @Test
    void testToDTO() {
        entity = new CategoryEntity();
        entity.setCategoryName("a lsd là Lá");
        entity.setCategoryCode("alsdlala");
        entity.setDescription("description");
        entity.setImagePath("ImagePath");
        entity.setCategoryID(5);
        entity.setStatus(STATUS.NOT_AVAILABLE.name());

        assertDTO = new CategoryDTO();
        assertDTO.setStatus(STATUS.NOT_AVAILABLE.name());
        assertDTO.setCategoryName("a lsd là Lá");
        assertDTO.setCategoryCode("alsdlala");
        assertDTO.setDescription("description");
        assertDTO.setImagePath("ImagePath");
        assertDTO.setCategoryID(5);

        CategoryDTO result = CategoryDTO.toDTO(entity);

        assertTrue(result.getCategoryID() == assertDTO.getCategoryID());
        assertTrue(result.getCategoryCode() == assertDTO.getCategoryCode());
        assertTrue(result.getCategoryName() == assertDTO.getCategoryName());
        assertTrue(result.getDescription() == assertDTO.getDescription());
        assertTrue(result.getStatus() == assertDTO.getStatus());
        assertTrue(result.getImagePath() == assertDTO.getImagePath());
    }

    @Test
    public void TestToEntity(){
        dto = new CategoryDTO();
        dto.setCategoryID(5);
        dto.setCategoryName("a lsd là Lá");
        dto.setDescription("description");
        dto.setImagePath("ImagePath");

        entity = new CategoryEntity();
        entity.setCategoryID(5);
        entity.setCategoryName("a lsd là Lá");
        entity.setDescription("description");
        entity.setImagePath("ImagePath");

        CategoryEntity result = CategoryDTO.toEntity(dto);

        assertTrue(result.getCategoryName() == entity.getCategoryName());
        assertTrue(result.getCategoryID() == entity.getCategoryID());
        assertTrue(result.getDescription() == entity.getDescription());
        assertTrue(result.getImagePath() == entity.getImagePath());
    }

}
