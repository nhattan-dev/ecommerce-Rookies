package com.nhattan.ecommerce.dto.request;

import com.nhattan.ecommerce.dto.UserDTO;
import com.nhattan.ecommerce.entity.UserEntity;
import com.nhattan.ecommerce.enums.GENDER;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class CreateUserRequestDTOTest {

    @Test
    public void testToEntity(){
        UserEntity entity = new UserEntity();
        entity.setEmail("setEmail");
        entity.setFirstName("setFirstName");
        entity.setGender(GENDER.FEMALE.name());
        entity.setLastName("setLastName");
        entity.setPhoneNumber("0987977356");
        Date dob = new Date();
        entity.setDateOfBirth(dob);

        UserDTO dto = new UserDTO();
        dto.setEmail("setEmail");
        dto.setFirstName("setFirstName");
        dto.setGender(GENDER.FEMALE.name());
        dto.setLastName("setLastName");
        dto.setPhoneNumber("0987977356");
        dto.setDateOfBirth(dob);

        UserDTO result = UserDTO.toDTO(entity);

        assertTrue(result.getEmail() == dto.getEmail());
        assertTrue(result.getFirstName() == dto.getFirstName());
        assertTrue(result.getLastName() == dto.getLastName());
        assertTrue(result.getGender() == dto.getGender());
        assertTrue(result.getPhoneNumber() == dto.getPhoneNumber());
        assertTrue(result.getDateOfBirth() == dto.getDateOfBirth());
    }
}
