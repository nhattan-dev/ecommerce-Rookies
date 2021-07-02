package com.nhattan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhattan.ecommerce.entity.ProductPriceEntity;

public interface IProductPriceRepository extends JpaRepository<ProductPriceEntity, Integer> {
	@Query(value = "SELECT price FROM ProductPrice WHERE productID = :productID "
			+ "AND date = (SELECT MAX(date) FROM ProductPrice WHERE productID = :productID)", nativeQuery = true)
	int getLatestPriceByProductID(@Param("productID") int productID);
}
