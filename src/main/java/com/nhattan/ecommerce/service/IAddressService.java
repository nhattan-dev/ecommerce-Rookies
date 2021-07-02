package com.nhattan.ecommerce.service;

import java.util.List;

import com.nhattan.ecommerce.request.CreateAddressRequest;
import com.nhattan.ecommerce.request.UpdateAddressRequest;
import com.nhattan.ecommerce.response.AddressResponse;

public interface IAddressService {
	AddressResponse save(CreateAddressRequest addressRequest);
	AddressResponse update(UpdateAddressRequest addressRequest);
	void delete(Integer addressID);
	List<AddressResponse> showAll(int customerID);
}
