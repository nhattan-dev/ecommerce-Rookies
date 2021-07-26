package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.AddressEntity;
import com.nhattan.ecommerce.entity.CustomerEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

@Getter
@Setter
public class AddressDTO {
    @Min(message = "must-be-greater-than-or-equals-0", value = 0)
    private int addressID;
    @NotBlank(message = "cannot-be-empty")
    private String fullname;
    @NotBlank(message = "cannot-be-empty")
    private String address;
    @NotBlank(message = "cannot-be-empty")
    private String phoneNumber;
    private int customerID;

    public static AddressEntity toEntity(AddressDTO dto){
        AddressEntity entity = new AddressEntity();
        entity.setAddressID(dto.getAddressID());
        entity.setAddress(dto.getAddress());
        entity.setFullname(dto.getFullname());
        entity.setPhoneNumber(dto.getPhoneNumber());
        CustomerEntity customer = new CustomerEntity();
        customer.setCustomerID(dto.customerID);
        entity.setCustomer(customer);
        return  entity;
    }

    public static AddressDTO toDTO(AddressEntity entity){
        AddressDTO dto = new AddressDTO();
        dto.setAddressID(entity.getAddressID());
        dto.setAddress(entity.getAddress());
        dto.setFullname(entity.getFullname());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }
}
