package com.nhattan.ecommerce.repository;

import com.nhattan.ecommerce.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IOrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {
//	@Query(value = "SELECT TOP 1 * FROM OrderDetail od WHERE od.orderID = (SELECT o.orderID FROM Orders o " +
//			"WHERE o.customerID = :customerID) AND od.productID = :productID", nativeQuery = true)
//	Optional<OrderDetailEntity> findByProductIDAndCustomerID(@Param("productID") int productID,
//															 @Param("customerID") int customerID);
	@Query(value = "SELECT TOP 1 * FROM OrderDetail od WHERE od.orderID = (SELECT o.orderID FROM Orders o " +
			"WHERE o.customerID = :customerID) AND od.productID = :productID", nativeQuery = true)
	OrderDetailEntity findByProductIDAndCustomerID(@Param("productID") int productID,
															 @Param("customerID") int customerID);

	boolean existsByProduct_ProductIDAndOrder_Customer_CustomerID(int productID , int customerID);
}
