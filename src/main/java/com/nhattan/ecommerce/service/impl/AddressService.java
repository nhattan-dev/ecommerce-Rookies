package com.nhattan.ecommerce.service.impl;

import com.nhattan.ecommerce.dto.AddressDTO;
import com.nhattan.ecommerce.entity.AddressEntity;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.IAddressRepository;
import com.nhattan.ecommerce.repository.ICustomerRepository;
import com.nhattan.ecommerce.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class AddressService implements IAddressService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Transactional
    @Override
    public AddressDTO save(AddressDTO dto) {
        if (!customerRepository.existsById(dto.getCustomerID()))
            throw new NotFoundException("customer-not-found");

        int MaxNumberAddress = 10;
        if (addressRepository.countAddressByCustomerID(dto.getCustomerID()) >= MaxNumberAddress)
            throw new ConflictException("maximum-number-address");

        AddressEntity newAddress = AddressDTO.toEntity(dto);
        return AddressDTO.toDTO(addressRepository.save(newAddress));
    }

    @Transactional
    @Override
    public AddressDTO update(AddressDTO dto) {
        if (!addressRepository.existsById(dto.getAddressID()))
            throw new NotFoundException("address-not-found");
        addressRepository.updateAddressByAddressID(dto.getFullname(), dto.getAddress(),
                dto.getPhoneNumber() ,dto.getAddressID());
        return AddressDTO.toDTO(addressRepository.findById(dto.getAddressID()).get());
    }

    @Transactional
    @Override
    public void delete(Integer addressID) {
        if (!addressRepository.existsById(addressID))
            throw new NotFoundException("address-not-found");
        addressRepository.deleteById(addressID);
    }

    @Override
    public List<AddressDTO> showAll(int customerID) {
        if (!customerRepository.existsById(customerID))
            throw new NotFoundException("customer-not-found");
        return addressRepository.findByCustomerID(customerID).stream().map(x -> AddressDTO.toDTO(x))
                .collect(Collectors.toList());
    }

}
