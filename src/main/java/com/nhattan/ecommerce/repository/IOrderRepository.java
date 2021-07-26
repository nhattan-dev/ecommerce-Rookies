package com.nhattan.ecommerce.repository;

import com.nhattan.ecommerce.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderEntity, Integer> {
	@Modifying
	@Query(value = "UPDATE Orders SET transactStatus = :transactStatus WHERE orderID = :orderID", nativeQuery = true)
	void updateTransactStutusByOrderID(@Param("transactStatus") String transactStatus, @Param("orderID") int orderID);

	@Query(value = "SELECT * FROM ORDERS WHERE customerID = :customerID", nativeQuery = true)
	List<OrderEntity> findOrdersByCustomerID(@Param("customerID") int customerID);

	List<OrderEntity> findOrderByTransactStatus(String transactStatus);
	
	@Query(value = "SELECT * FROM Orders WHERE orderID = :orderID AND customerID = :customerID", nativeQuery = true)
	Optional<OrderEntity> findOneByOrderIDAndCustomerID(@Param("orderID") int orderID, @Param("customerID") int customerID);
	
	@Query(value = "SELECT CAST(SUBSTRING(ISNULL(MAX(orderCode), 'OC000000000'), 3, 11) AS INT) FROM Orders", nativeQuery = true)
	int getMaxOrderCode();
}
