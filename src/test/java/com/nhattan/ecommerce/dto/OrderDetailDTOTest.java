package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.OrderDetailEntity;
import com.nhattan.ecommerce.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class OrderDetailDTOTest {

    @Test
    public void TestToEntity(){
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setQuantity(9867);
        dto.setProductID(34);
        dto.setSize("setSize");
        dto.setOrderDetailID(23);
        dto.setPrice(3487654);
        dto.setColor("setColor");

        OrderDetailEntity entity = new OrderDetailEntity();
        entity.setQuantity(9867);
        entity.setSize("setSize");
        entity.setOrderDetailID(23);
        entity.setPrice(3487654);
        entity.setColor("setColor");
        ProductEntity p = new ProductEntity();
        p.setProductID(34);
        entity.setProduct(p);

        OrderDetailEntity result = OrderDetailDTO.toEntity(dto);

        assertTrue(result.getColor() == entity.getColor());
        assertTrue(result.getSize() == entity.getSize());
        assertTrue(result.getProduct().getProductID() == entity.getProduct().getProductID());
        assertTrue(result.getQuantity() == entity.getQuantity());
        assertTrue(result.getPrice() == entity.getPrice());
        assertTrue(result.getOrderDetailID() == entity.getOrderDetailID());
    }

    @Test
    public void TestToDTO(){
        OrderDetailEntity entity = new OrderDetailEntity();
        entity.setQuantity(9867);
        entity.setSize("setSize");
        entity.setOrderDetailID(23);
        entity.setPrice(3487654);
        entity.setColor("setColor");
        ProductEntity p = new ProductEntity();
        p.setProductID(34);
        entity.setProduct(p);

        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setQuantity(9867);
        dto.setProductID(34);
        dto.setSize("setSize");
        dto.setOrderDetailID(23);
        dto.setPrice(3487654);
        dto.setColor("setColor");

        OrderDetailDTO result = OrderDetailDTO.toDTO(entity);

        assertTrue(result.getColor() == dto.getColor());
        assertTrue(result.getSize() == dto.getSize());
        assertTrue(result.getProductID() == dto.getProductID());
        assertTrue(result.getQuantity() == dto.getQuantity());
        assertTrue(result.getPrice() == dto.getPrice());
        assertTrue(result.getOrderDetailID() == entity.getOrderDetailID());
    }
}
