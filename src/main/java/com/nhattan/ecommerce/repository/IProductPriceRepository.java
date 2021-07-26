package com.nhattan.ecommerce.repository;

import com.nhattan.ecommerce.entity.ProductPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductPriceRepository extends JpaRepository<ProductPriceEntity, Integer> {
	@Query(value = "SELECT TOP 1 price FROM ProductPrice WHERE productID = :productID ORDER BY date DESC", nativeQuery = true)
	int getLatestPriceByProductID(@Param("productID") int productID);
}
