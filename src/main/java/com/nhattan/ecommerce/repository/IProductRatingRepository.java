package com.nhattan.ecommerce.repository;

import com.nhattan.ecommerce.entity.ProductRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductRatingRepository extends JpaRepository<ProductRatingEntity, Integer> {
	@Query(value = "SELECT TOP 1 * FROM ProductRating WHERE customerID = :customerID AND productID = :productID", nativeQuery = true)
	ProductRatingEntity findOneByCustomerIDAndProductID(@Param("customerID") int customerID,
			@Param("productID") int productID);

	ProductRatingEntity findByCustomer_CustomerIDAndProduct_ProductID(int customerID, int productID);
}
