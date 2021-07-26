package com.nhattan.ecommerce.repository;

import com.nhattan.ecommerce.entity.AddressEntity;
import com.nhattan.ecommerce.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IAddressRepository extends JpaRepository<AddressEntity, Integer> {

    boolean existsByAddressIDAndCustomer_CustomerID(int addressID, int customerID);

    @Query(value = "SELECT COUNT(1) FROM Addresses WHERE customerID = :customerID", nativeQuery = true)
    int countAddressByCustomerID(@Param("customerID") int customerID);

    @Modifying
    @Query(value = "UPDATE Addresses SET fullname = :fullname, address = :address, phoneNumber = :phoneNumber " +
            "WHERE addressID = :addressID", nativeQuery = true)
    void updateAddressByAddressID(@Param("fullname") String fullname, @Param("address") String address,
            @Param("phoneNumber") String phoneNumber, @Param("addressID") int addressID);

    @Query(value = "SELECT * FROM Addresses WHERE customerID = :customerID", nativeQuery = true)
    List<AddressEntity> findByCustomerID(@Param("customerID") int customerID);

    @Query(value = "SELECT * FROM Addresses WHERE addressID = :addressID AND customerID = :customerID", nativeQuery = true)
    Optional<AddressEntity> findOneByAddressIDAndCustomerID(@Param("addressID") int addressID, @Param("customerID") int customerID);
}
