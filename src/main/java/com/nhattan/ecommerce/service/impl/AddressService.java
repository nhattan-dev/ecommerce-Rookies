package com.nhattan.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.nhattan.ecommerce.entity.AddressEntity;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.IAddressRepository;
import com.nhattan.ecommerce.repository.ICustomerRepository;
import com.nhattan.ecommerce.request.CreateAddressRequest;
import com.nhattan.ecommerce.request.UpdateAddressRequest;
import com.nhattan.ecommerce.response.AddressResponse;
import com.nhattan.ecommerce.service.IAddressService;

public class AddressService implements IAddressService {

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private IAddressRepository addressRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public AddressResponse save(CreateAddressRequest addressRequest) {
		if (!customerRepository.exists(addressRequest.getCustomerID()))
			throw new NotFoundException("customer-not-found");

		int MaxNumberAddress = 10;
		if (addressRepository.countAddressByCustomerID(addressRequest.getCustomerID()) >= MaxNumberAddress)
			throw new ConflictException("maximum-number-address");

		AddressEntity newAddress = modelMapper.map(addressRequest, AddressEntity.class);
		return modelMapper.map(addressRepository.save(newAddress), AddressResponse.class);
	}

	@Transactional
	@Override
	public AddressResponse update(UpdateAddressRequest addressRequest) {
		if (!addressRepository.exists(addressRequest.getAddressID()))
			throw new NotFoundException("address-not-found");
		addressRepository.updateAddressByAddressID(addressRequest.getFullname(), addressRequest.getAddress(),
				addressRequest.getAddressID());
		return modelMapper.map(addressRepository.findOne(addressRequest.getAddressID()), AddressResponse.class);
	}

	@Transactional
	@Override
	public void delete(Integer addressID) {
		if (!addressRepository.exists(addressID))
			throw new NotFoundException("address-not-found");
		addressRepository.delete(addressID);
	}

	@Override
	public List<AddressResponse> showAll(int customerID) {
		if (!customerRepository.exists(customerID))
			throw new NotFoundException("customer-not-found");
		return addressRepository.findByCustomerID(customerID).stream()
				.map(x -> modelMapper.map(x, AddressResponse.class)).collect(Collectors.toList());
	}

}
