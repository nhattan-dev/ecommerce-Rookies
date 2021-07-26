package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.CustomerEntity;
import com.nhattan.ecommerce.entity.RoleEntity;
import com.nhattan.ecommerce.entity.UserEntity;
import com.nhattan.ecommerce.enums.ACCOUNT_STATUS;
import com.nhattan.ecommerce.enums.GENDER;
import com.nhattan.ecommerce.enums.ROLE;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class UserDTOTest {

    @Test
    public void TestToDTO(){
        UserEntity entity = new UserEntity();
        entity.setEmail("setEmail");
        entity.setFirstName("setFirstName");
        entity.setGender(GENDER.FEMALE.name());
        entity.setLastName("setLastName");
        entity.setPhoneNumber("0987977356");
        entity.setStatus(ACCOUNT_STATUS.NOT_AVAILABLE.name());
        entity.setUserID(5);
        RoleEntity role = new RoleEntity();
        role.setRole(ROLE.ROLE_USER.name());
        entity.setRole(role);
        Date dob = new Date();
        entity.setDateOfBirth(dob);
        CustomerEntity customer = new CustomerEntity();
        customer.setCustomerID(88);
        entity.setCustomer(customer);

        UserDTO dto = new UserDTO();
        dto.setEmail("setEmail");
        dto.setFirstName("setFirstName");
        dto.setGender(GENDER.FEMALE.name());
        dto.setLastName("setLastName");
        dto.setPhoneNumber("0987977356");
        dto.setStatus(ACCOUNT_STATUS.NOT_AVAILABLE.name());
        dto.setUserID(5);
        dto.setRole(ROLE.ROLE_USER.name());
        dto.setDateOfBirth(dob);
        dto.setCustomerID(88);

        UserDTO result = UserDTO.toDTO(entity);

        assertTrue(result.getEmail() == dto.getEmail());
        assertTrue(result.getFirstName() == dto.getFirstName());
        assertTrue(result.getLastName() == dto.getLastName());
        assertTrue(result.getGender() == dto.getGender());
        assertTrue(result.getRole() == dto.getRole());
        assertTrue(result.getPhoneNumber() == dto.getPhoneNumber());
        assertTrue(result.getDateOfBirth() == dto.getDateOfBirth());
        assertTrue(result.getUserID() == dto.getUserID());
        assertTrue(result.getStatus() == dto.getStatus());
    }
}
