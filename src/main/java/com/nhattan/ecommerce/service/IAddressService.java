package com.nhattan.ecommerce.service;

import com.nhattan.ecommerce.dto.AddressDTO;

import java.util.List;

public interface IAddressService {
	AddressDTO save(AddressDTO addressRequest);
	AddressDTO update(AddressDTO addressRequest);
	void delete(Integer addressID);
	List<AddressDTO> showAll(int customerID);
}
