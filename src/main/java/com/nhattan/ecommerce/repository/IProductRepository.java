package com.nhattan.ecommerce.repository;

import java.util.List;
import java.util.Optional;

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

	public List<ProductEntity> findByDeletedEquals(int status, Pageable pageable);

	ProductEntity findProductByDeletedAndProductID(int value, int productID);

	@Modifying
	@Query(value = "UPDATE Product SET quantity = :newQuantity WHERE productID = :productID", nativeQuery = true)
	void updateQuantityByProductID(@Param("newQuantity") int newQuantity, @Param("productID") int productID);

	@Query(value = "SELECT * FROM Product WHERE productID = :productID AND deleted = :value AND subcategoryID "
			+ "IN (SELECT s.subcategoryID FROM (SELECT subcategoryID, categoryID FROM Subcategory WHERE "
			+ "Subcategory.deleted = :value) AS s INNER JOIN (SELECT categoryID FROM Category WHERE "
			+ "Category.deleted = :value) AS c ON s.categoryID = c.categoryID)", nativeQuery = true)
	Optional<ProductEntity> findProductValidByProductID(@Param("value") int ValueNotDeleted,
			@Param("productID") int productID);

	@Query(value = "SELECT productID, productName, description, quantity, point, subcategoryID, deleted "
			+ "FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY Product.productName :sort) AS top_number "
			+ "FROM Product WHERE deleted = 1 OR subcategoryID IN (SELECT subcategoryID FROM Subcategory "
			+ "WHERE deleted = 1 OR subcategoryID IN (SELECT subcategoryID FROM Category "
			+ "WHERE Category.deleted = 1 AND Category.categoryID = Subcategory.categoryID))) AS rs "
			+ "WHERE top_number BETWEEN (:page - 1) * :limit + 1 AND :page * :limit", nativeQuery = true)
	List<ProductEntity> findProductNotAvailable(@Param("page") int page, @Param("limit") int limit,
			@Param("sort") String sort);

	@Query(value = "SELECT COUNT(1) FROM Product WHERE deleted = 1 OR subcategoryID IN (SELECT subcategoryID FROM Subcategory "
			+ "WHERE deleted = 1 OR subcategoryID IN (SELECT subcategoryID FROM Category WHERE Category.deleted = 1 "
			+ "AND Category.categoryID = Subcategory.categoryID))", nativeQuery = true)
	int countProductNotAvailable();

	@Modifying
	@Query(value = "UPDATE Product SET point = (SELECT SUM(pr.point) FROM ProductRating pr "
			+ "WHERE pr.productID = :productID) / ISNULL(NULLIF((SELECT COUNT(1) FROM ProductRating pr "
			+ "WHERE pr.productID = :productID), 0), 1) WHERE productID = :productID", nativeQuery = true)
	void updateProductPointByProductID(@Param("productID") int productID);

	@Query(value = "SELECT * FROM Product WHERE productID = :productID AND deleted = :value AND (SELECT Subcategory.deleted "
			+ "FROM Subcategory WHERE Product.subcategoryID = Subcategory.subcategoryID "
			+ "AND (SELECT Category.deleted FROM Category WHERE Category.categoryID = "
			+ "Subcategory.categoryID) = :value) = :value", nativeQuery = true)
	Optional<ProductEntity> findOneValid(@Param("value") int value, @Param("productID") int productID);

	@Query(value = "UPDATE Product SET deleted = :value WHERE productID = :productID", nativeQuery = true)
	void reactivityProduct(@Param("productID") int productID, @Param("value") int value);

	@Query(value = "SELECT productID, productName, description, quantity, point, subcategoryID, deleted "
			+ "FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY Product.productName :sort) AS top_number "
			+ "FROM Product WHERE deleted = 0 AND subcategoryID IN (SELECT s.subcategoryID "
			+ "FROM (SELECT subcategoryID, categoryID FROM Subcategory WHERE Subcategory.deleted = 0) AS s "
			+ "INNER JOIN (SELECT categoryID FROM Category WHERE Category.deleted = 0) AS c ON s.categoryID = c.categoryID)) "
			+ "AS rs WHERE top_number BETWEEN (:page - 1) * :limit + 1 AND :page * :limit", nativeQuery = true)
	List<ProductEntity> findProductAvailable(@Param("page") int page, @Param("limit") int limit,
			@Param("sort") String sort);

	@Query(value = "SELECT COUNT(1) FROM Product WHERE deleted = 0 AND subcategoryID IN (SELECT s.subcategoryID FROM (SELECT subcategoryID, "
			+ "categoryID FROM Subcategory WHERE Subcategory.deleted = 0) AS s INNER JOIN (SELECT categoryID FROM Category WHERE Category.deleted = 0) "
			+ "AS c ON s.categoryID = c.categoryID)", nativeQuery = true)
	int countProductAvailable();

	@Query(value = "SELECT productID, productName, description, quantity, point, subcategoryID, deleted "
			+ "FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY Product.productName :sort) AS top_number "
			+ "FROM Product WHERE subcategoryID = :subcategoryID AND deleted = 0 AND subcategoryID IN (SELECT s.subcategoryID "
			+ "FROM (SELECT subcategoryID, categoryID FROM Subcategory WHERE Subcategory.deleted = 0) AS s "
			+ "INNER JOIN (SELECT categoryID FROM Category WHERE Category.deleted = 0) AS c ON s.categoryID = c.categoryID)) "
			+ "AS rs WHERE top_number BETWEEN (:page - 1) * :limit + 1 AND :page * :limit", nativeQuery = true)
	List<ProductEntity> findProductAvailableBySubcategory(@Param("page") int page, @Param("limit") int limit,
			@Param("sort") String sort, @Param("subcategoryID") int subcategoryID);

	@Query(value = "SELECT COUNT(1) FROM Product WHERE subcategoryID = :subcategoryID AND deleted = 0 AND subcategoryID IN (SELECT s.subcategoryID "
			+ "FROM (SELECT subcategoryID, categoryID FROM Subcategory WHERE Subcategory.deleted = 0) AS s INNER JOIN (SELECT categoryID FROM Category "
			+ "WHERE Category.deleted = 0) AS c ON s.categoryID = c.categoryID)", nativeQuery = true)
	int countProductAvailableBySubcategory(@Param("subcategoryID") int subcategoryID);
}
