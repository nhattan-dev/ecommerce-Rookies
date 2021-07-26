package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import java.util.Date;

@Getter
@Setter
public class UserDTO {
    @Min(message = "must-be-greater-than-or-equals-0", value = 0)
    private int userID;
    @NotBlank(message = "cannot-be-empty")
    private String email;
    private String phoneNumber;
    @NotBlank(message = "cannot-be-empty")
    private String firstName;
    @NotBlank(message = "cannot-be-empty")
    private String lastName;
    private String gender;
    private String status;
    private Date dateOfBirth = new Date();
    private int customerID;
    private String role;

    public static UserDTO toDTO(UserEntity entity){
        UserDTO dto = new UserDTO();
        dto.setStatus(entity.getStatus());
//        dto.setCustomerID(entity.getCustomer().getCustomerID());
        dto.setEmail(entity.getEmail());
        dto.setGender(entity.getGender());
        dto.setUserID(entity.getUserID());
        dto.setFirstName(entity.getFirstName());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }
}
