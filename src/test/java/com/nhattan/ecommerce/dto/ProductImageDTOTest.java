package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.ProductEntity;
import com.nhattan.ecommerce.entity.ProductImageEntity;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class ProductImageDTOTest {

    @Test
    public void TestToEntity(){
        ProductImageDTO dto = new ProductImageDTO();
        dto.setProductID(5);
        dto.setProductImageID(9);
        dto.setImagePath("setImagePath");

        ProductImageEntity entity = new ProductImageEntity();
        entity.setProductImageID(9);
        entity.setImagePath("setImagePath");
        ProductEntity product = new ProductEntity();
        product.setProductID(5);
        entity.setProduct(product);

        ProductImageEntity result = ProductImageDTO.toEntity(dto);

        assertTrue(result.getImagePath() == entity.getImagePath());
        assertTrue(result.getProduct().getProductID() == entity.getProduct().getProductID());
        assertTrue(result.getProductImageID() == entity.getProductImageID());
    }

    @Test
    public void TestToDTO(){
        ProductImageDTO dto = new ProductImageDTO();
        dto.setProductID(5);
        dto.setProductImageID(9);
        dto.setImagePath("setImagePath");

        ProductImageEntity entity = new ProductImageEntity();
        entity.setProductImageID(9);
        entity.setImagePath("setImagePath");
        ProductEntity product = new ProductEntity();
        product.setProductID(5);
        entity.setProduct(product);

        ProductImageDTO result = ProductImageDTO.toDTO(entity);

        assertTrue(result.getImagePath() == dto.getImagePath());
        assertTrue(result.getProductID() == dto.getProductID());
        assertTrue(result.getProductImageID() == dto.getProductImageID());
    }
}
