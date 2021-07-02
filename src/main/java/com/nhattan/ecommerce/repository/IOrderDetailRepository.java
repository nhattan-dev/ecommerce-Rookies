package com.nhattan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhattan.ecommerce.entity.OrderDetailEntity;

public interface IOrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {
	@Query(value = "SELECT TOP 1 * FROM OrderDetail od WHERE od.productID = :productID "
			+ "AND (SELECT customerID FROM Orders o WHERE o.orderID = od.orderID) = :customerID", nativeQuery = true)
	OrderDetailEntity findOneByProductIDAndCustomerID(@Param("productID") int productID,
			@Param("customerID") int customerID);
}
