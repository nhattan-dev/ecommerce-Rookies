package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.ProductEntity;
import com.nhattan.ecommerce.entity.SubcategoryEntity;
import com.nhattan.ecommerce.enums.STATUS;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

//import org.junit.Test;

class ProductDTOTest {

    @Test
    void testToEntity() {
        ProductDTO dto = new ProductDTO();
        dto.setDescription("setDescription");
        dto.setSubcategoryID(5);
        dto.setStatus(STATUS.NOT_AVAILABLE.name());
        dto.setName("setName");
        dto.setQuantity(50);

        ProductEntity entity = new ProductEntity();
        entity.setDescription("setDescription");
        entity.setStatus(STATUS.NOT_AVAILABLE.name());
        entity.setName("setName");
        entity.setQuantity(50);
        SubcategoryEntity sub = new SubcategoryEntity();
        sub.setSubcategoryID(5);
        entity.setSubcategory(sub);
    }

    @Test
    void testToDTO() {
        ProductDTO dto = new ProductDTO();
        dto.setProductID(90);
        dto.setStatus(STATUS.NOT_AVAILABLE.name());
        dto.setQuantity(10);
        dto.setName("setName");
        dto.setSubcategoryID(3);
        dto.setDescription("setDescription");
        dto.setPoint(5);

        ProductEntity entity = new ProductEntity();
        entity.setProductID(90);
        entity.setStatus(STATUS.NOT_AVAILABLE.name());
        entity.setQuantity(10);
        entity.setName("setName");
        entity.setDescription("setDescription");
        entity.setPoint(5);
        SubcategoryEntity sub = new SubcategoryEntity();
        sub.setSubcategoryID(3);
        entity.setSubcategory(sub);

        ProductDTO result = ProductDTO.toDTO(entity);

        assertTrue(result.getDescription() == dto.getDescription());
        assertTrue(result.getName() == dto.getName());
        assertTrue(result.getStatus() == dto.getStatus());
        assertTrue(result.getProductID() == dto.getProductID());
        assertTrue(result.getPoint() == dto.getPoint());
        assertTrue(result.getSubcategoryID() == dto.getSubcategoryID());
        assertTrue(result.getPoint() == dto.getPoint());
    }

}
