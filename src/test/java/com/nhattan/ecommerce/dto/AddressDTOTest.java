package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.AddressEntity;
import com.nhattan.ecommerce.enums.STATUS;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

class AddressDTOTest {
    AddressDTO dto, assertDTO;
    AddressEntity entity;

    @Test
    void testToDTO() {
        entity = new AddressEntity();
        entity.setAddressID(5);
        entity.setAddress("address");
        entity.setFullname("fullname");
        entity.setPhoneNumber("phoneNumber");

        assertDTO = new AddressDTO();
        assertDTO.setAddressID(5);
        assertDTO.setAddress("address");
        assertDTO.setFullname("fullname");
        assertDTO.setPhoneNumber("phoneNumber");

        AddressDTO result = AddressDTO.toDTO(entity);

        assertTrue(result.getAddressID() == assertDTO.getAddressID());
        assertTrue(result.getFullname() == assertDTO.getFullname());
        assertTrue(result.getPhoneNumber() == assertDTO.getPhoneNumber());
        assertTrue(result.getAddress() == assertDTO.getAddress());
    }

    @Test
    public void TestToEntity(){
        dto = new AddressDTO();
        dto.setAddressID(5);
        dto.setAddress("address");
        dto.setFullname("fullname");
        dto.setPhoneNumber("phoneNumber");

        entity = new AddressEntity();
        entity.setAddressID(5);
        entity.setAddress("address");
        entity.setFullname("fullname");
        entity.setPhoneNumber("phoneNumber");

        AddressEntity result = AddressDTO.toEntity(dto);

        assertTrue(result.getAddressID() == entity.getAddressID());
        assertTrue(result.getFullname() == entity.getFullname());
        assertTrue(result.getPhoneNumber() == entity.getPhoneNumber());
        assertTrue(result.getAddress() == entity.getAddress());
    }

}
