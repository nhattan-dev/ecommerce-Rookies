package com.nhattan.ecommerce.service;

import java.util.List;

import com.nhattan.ecommerce.dto.AddressDTO;

public interface IAddressService {
	AddressDTO save(AddressDTO addressRequest);
	AddressDTO update(AddressDTO addressRequest);
	void delete(Integer addressID);
	List<AddressDTO> showAll(int customerID);
}
