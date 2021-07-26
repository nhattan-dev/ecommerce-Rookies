package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.ProductSizeEntity;
import com.nhattan.ecommerce.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class ProductSizeDTOTest {

    @Test
    public void TestToEntity(){
        ProductSizeDTO dto = new ProductSizeDTO();
        dto.setProductID(5);
        dto.setProductSizeID(9);
        dto.setSize("setSize");

        ProductSizeEntity entity = new ProductSizeEntity();
        entity.setProductSizeID(9);
        entity.setSize("setSize");
        ProductEntity product = new ProductEntity();
        product.setProductID(5);
        entity.setProduct(product);

        ProductSizeEntity result = ProductSizeDTO.toEntity(dto);

        assertTrue(result.getSize() == entity.getSize());
        assertTrue(result.getProduct().getProductID() == entity.getProduct().getProductID());
        assertTrue(result.getProductSizeID() == entity.getProductSizeID());
    }

    @Test
    public void TestToDTO(){
        ProductSizeDTO dto = new ProductSizeDTO();
        dto.setProductID(5);
        dto.setProductSizeID(9);
        dto.setSize("setSize");

        ProductSizeEntity entity = new ProductSizeEntity();
        entity.setProductSizeID(9);
        entity.setSize("setSize");
        ProductEntity product = new ProductEntity();
        product.setProductID(5);
        entity.setProduct(product);

        ProductSizeDTO result = ProductSizeDTO.toDTO(entity);

        assertTrue(result.getSize() == dto.getSize());
        assertTrue(result.getProductID() == dto.getProductID());
        assertTrue(result.getProductSizeID() == dto.getProductSizeID());
    }
}
