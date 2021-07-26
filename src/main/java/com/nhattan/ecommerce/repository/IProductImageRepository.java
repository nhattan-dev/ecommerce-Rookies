package com.nhattan.ecommerce.repository;

import com.nhattan.ecommerce.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IProductImageRepository extends JpaRepository<ProductImageEntity, Integer> {
//	Boolean existsByImagePathAndProductID(String imagePath, int productID);
	@Query(value = "SELECT * FROM ProductImage WHERE imagePath = :imagePath AND productID = :productID", nativeQuery = true)
	Optional<ProductImageEntity> findOneByImagePathAndProductID(@Param("imagePath") String imagePath,
			@Param("productID") int productID);
	boolean existsByImagePathAndProduct_ProductID(String imagePath, int productID);
}
