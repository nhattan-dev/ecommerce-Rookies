package com.nhattan.ecommerce.repository;

import com.nhattan.ecommerce.entity.ProductColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IProductColorRepository extends JpaRepository<ProductColorEntity, Integer> {
//	Boolean existsProductColorByColorLike(String color);
//	Boolean existsProductColorByColorAndProductID(String color, int productID);
	@Query(value = "SELECT * FROM ProductColor WHERE color = :color AND productID = :productID", nativeQuery = true)
	Optional<ProductColorEntity> findOneByColorAndProductID(@Param("color") String color,
			@Param("productID") int productID);

	boolean existsByColorAndProduct_ProductID(String color, int productID);
}
