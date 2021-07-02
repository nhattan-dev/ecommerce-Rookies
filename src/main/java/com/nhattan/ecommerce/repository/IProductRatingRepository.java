package com.nhattan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhattan.ecommerce.entity.ProductRatingEntity;

public interface IProductRatingRepository extends JpaRepository<ProductRatingEntity, Integer> {
	@Query(value = "SELECT TOP 1 * FROM ProductRating WHERE customerID = :customerID AND productID = :productID", nativeQuery = true)
	ProductRatingEntity findOneByCustomerIDAndProductID(@Param("customerID") int customerID,
			@Param("productID") int productID);
}
