package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.AddressEntity;
import com.nhattan.ecommerce.entity.CustomerEntity;
import com.nhattan.ecommerce.entity.OrderEntity;
import com.nhattan.ecommerce.enums.ORDER_STATUS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class OrderDTOTest {

    @Test
    public void TestToEntity(){
        OrderDTO dto = new OrderDTO();
        dto.setCustomerID(77);
        dto.setOrderID(98);
        dto.setAddressID(23);

        OrderEntity entity = new OrderEntity();
        entity.setOrderID(98);
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressID(23);
        entity.setAddress(addressEntity);

        OrderEntity result = OrderDTO.toEntity(dto);

        Assertions.assertEquals(result.getOrderID(), entity.getOrderID());
        Assertions.assertEquals(result.getAddress().getAddressID(), entity.getAddress().getAddressID());
    }

    @Test
    public void TestToDTO(){
        OrderEntity entity = new OrderEntity();
        entity.setOrderID(98);
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerID(77);
        entity.setCustomer(customerEntity);
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressID(23);
        entity.setAddress(addressEntity);
        Date orderDate = new Date();
        entity.setOrderDate(orderDate);
        entity.setTransactStatus(ORDER_STATUS.CONFIRMED.name());
        entity.setOrderCode("setOrderCode");

        OrderDTO dto = new OrderDTO();
        dto.setCustomerID(77);
        dto.setOrderID(98);
        dto.setAddressID(23);
        dto.setOrderDate(orderDate);
        dto.setTransactStatus(ORDER_STATUS.CONFIRMED.name());
        dto.setOrderCode("setOrderCode");

        OrderDTO result = OrderDTO.toDTO(entity);

        assertTrue(result.getTransactStatus() == dto.getTransactStatus());
        assertTrue(result.getOrderCode() == dto.getOrderCode());
        assertTrue(result.getOrderID() == dto.getOrderID());
        assertTrue(result.getOrderDate() == dto.getOrderDate());
        assertTrue(result.getAddressID() == dto.getAddressID());
        assertTrue(result.getCustomerID() == dto.getCustomerID());
    }
}
