package com.nhattan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhattan.ecommerce.entity.CustomerEntity;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Integer> {
}
