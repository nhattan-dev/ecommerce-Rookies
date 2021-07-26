package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.ProductEntity;
import com.nhattan.ecommerce.entity.ProductColorEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductColorDTOTest {

    @Test
    public void TestToEntity(){
        ProductColorDTO dto = new ProductColorDTO();
        dto.setProductID(5);
        dto.setProductColorID(9);
        dto.setColor("setColor");

        ProductColorEntity entity = new ProductColorEntity();
        entity.setProductColorID(9);
        entity.setColor("setColor");
        ProductEntity product = new ProductEntity();
        product.setProductID(5);
        entity.setProduct(product);

        ProductColorEntity result = ProductColorDTO.toEntity(dto);

        Assertions.assertEquals(result.getColor(), entity.getColor());
        Assertions.assertEquals(result.getProduct().getProductID(), entity.getProduct().getProductID());
        Assertions.assertEquals(result.getProductColorID(), entity.getProductColorID());
    }

    @Test
    public void TestToDTO(){
        ProductColorDTO dto = new ProductColorDTO();
        dto.setProductID(5);
        dto.setProductColorID(9);
        dto.setColor("setColor");

        ProductColorEntity entity = new ProductColorEntity();
        entity.setProductColorID(9);
        entity.setColor("setColor");
        ProductEntity product = new ProductEntity();
        product.setProductID(5);
        entity.setProduct(product);

        ProductColorDTO result = ProductColorDTO.toDTO(entity);

        Assertions.assertEquals(result.getColor(), dto.getColor());
        Assertions.assertEquals(result.getProductID(), dto.getProductID());
        Assertions.assertEquals(result.getProductColorID(), dto.getProductColorID());
    }
}
