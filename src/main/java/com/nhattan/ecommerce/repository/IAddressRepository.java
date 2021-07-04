package com.nhattan.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhattan.ecommerce.entity.AddressEntity;
import com.nhattan.ecommerce.entity.CustomerEntity;

public interface IAddressRepository extends JpaRepository<AddressEntity, Integer> {

	@Query(value = "SELECT COUNT(1) FROM Addresses WHERE customerID = :customerID", nativeQuery = true)
	int countAddressByCustomerID(@Param("customerID") int customerID);

	@Modifying
	@Query(value = "UPDATE Addresses SET fullname = :fullname, address = :address WHERE addressID = :addressID", nativeQuery = true)
	void updateAddressByAddressID(@Param("fullname") String fullname, @Param("address") String address,
			@Param("address") int addressID);

	@Query(value = "SELECT * FROM Address WHERE customerID = :customerID", nativeQuery = true)
	List<CustomerEntity> findByCustomerID(@Param("customerID") int customerID);
}
