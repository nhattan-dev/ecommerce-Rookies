package com.nhattan.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhattan.ecommerce.entity.OrderEntity;

public interface IOrderRepository extends JpaRepository<OrderEntity, Integer> {
	@Modifying
	@Query(value = "UPDATE Orders SET transactStatus = :value WHERE orderID = :orderID", nativeQuery = true)
	void updateTransactStutusByOrderID(@Param("value") int value, @Param("orderID") int orderID);

	@Query(value = "SELECT * FROM ORDERS WHERE customerID = :customerID", nativeQuery = true)
	List<OrderEntity> findOrdersByCustomerID(@Param("customerID") int customerID);

	List<OrderEntity> findOrderByTransactStatus(int value);
}
