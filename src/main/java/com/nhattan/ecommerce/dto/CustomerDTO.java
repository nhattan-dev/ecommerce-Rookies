package com.nhattan.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomerDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int customerID;
	private List<AddressDTO> addresses = new ArrayList<AddressDTO>();
	private List<OrderDTO> orders = new ArrayList<OrderDTO>();
}
