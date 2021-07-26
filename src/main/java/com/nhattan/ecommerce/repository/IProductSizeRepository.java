package com.nhattan.ecommerce.repository;

import com.nhattan.ecommerce.entity.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IProductSizeRepository extends JpaRepository<ProductSizeEntity, Integer> {
	@Query(value = "SELECT * FROM ProductSize WHERE size = :size AND productID = :productID", nativeQuery = true)
	Optional<ProductSizeEntity> findOneBySizeAndProductID(@Param("size") String size,
			@Param("productID") int productID);

    boolean existsBySizeAndProduct_ProductID(String size, int productID);

//	Boolean existsBySizeAndProductID(String size, int productID);
}
