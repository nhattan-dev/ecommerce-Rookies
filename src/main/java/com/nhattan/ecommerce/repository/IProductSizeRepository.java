package com.nhattan.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhattan.ecommerce.entity.ProductSizeEntity;

public interface IProductSizeRepository extends JpaRepository<ProductSizeEntity, Integer> {
	@Query(value = "SELECT * FROM ProductSize WHERE size = :size AND productID = :productID", nativeQuery = true)
	Optional<ProductSizeEntity> findOneBySizeAndProductID(@Param("size") String size,
			@Param("productID") int productID);

//	Boolean existsBySizeAndProductID(String size, int productID);
}
