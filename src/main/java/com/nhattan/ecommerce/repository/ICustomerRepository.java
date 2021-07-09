package com.nhattan.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhattan.ecommerce.entity.CustomerEntity;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Integer> {
	@Query(value = "SELECT * FROM Customer WHERE customerID = :customerID "
			+ "AND (SELECT valid FROM Users WHERE Users.customerID = Customer.customerID) = :value", nativeQuery = true)
	Optional<CustomerEntity> findByAvailableUser(@Param("customerID") int customerID, @Param("value") int valid);
}
