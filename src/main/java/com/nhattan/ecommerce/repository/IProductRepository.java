package com.nhattan.ecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhattan.ecommerce.entity.ProductEntity;

public interface IProductRepository extends JpaRepository<ProductEntity, Integer> {
	@Modifying
	@Query(value = "UPDATE Product SET deleted = 1 WHERE productID = :productID", nativeQuery = true)
	public void deleteByProductID(@Param("productID") int id);

	@Modifying
	@Query(value = "UPDATE Product SET productName = :productName, description = :description, quantity = :quantity, "
			+ "subcategoryID = :subcategoryID WHERE productID = :productID", nativeQuery = true)
	public void updateByProductID(@Param("productName") String productName, @Param("description") String description,
			@Param("subcategoryID") int subcategoryID, @Param("quantity") int quantity,
			@Param("productID") int productID);

//	@Query(value = "SELECT * FROM Product s WHERE s.deleted = 0", countQuery = "SELECT COUNT(*) FROM Product s WHERE s.deleted = 0", nativeQuery = true)
//	public Page<ProductEntity> findAllValid(Pageable pageable);

//	@Query(value = "SELECT s FROM Product s", nativeQuery = true)
//	public Page<ProductEntity> findAll(Pageable pageable);

	public List<ProductEntity> findByDeletedEquals(int status, Pageable pageable);

	ProductEntity findProductByDeletedAndProductID(int value, int productID);

	@Modifying
	@Query(value = "UPDATE Product SET quantity = :newQuantity WHERE productID = :productID", nativeQuery = true)
	void updateQuantityByProductID(@Param("newQuantity") int newQuantity, @Param("productID") int productID);

	@Query(value = "SELECT * FROM Product WHERE productID = :productID AND deleted = :value AND (SELECT Subcategory.deleted FROM Subcategory WHERE Subcategory.deleted = :value AND (SELECT Category.deleted FROM Category) = :value) = :value", nativeQuery = true)
	ProductEntity findProductValidByProductID(@Param("value") int ValueNotDeleted, @Param("productID") int productID);

	@Modifying
	@Query(value = "UPDATE Product SET point = (SELECT SUM(pr.point) FROM ProductRating pr "
			+ "WHERE pr.productID = :productID) / ISNULL(NULLIF((SELECT COUNT(1) FROM ProductRating pr "
			+ "WHERE pr.productID = :productID), 0), 1) WHERE productID = :productID", nativeQuery = true)
	void updateProductPointByProductID(@Param("productID") int productID);
}
