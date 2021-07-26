package com.nhattan.ecommerce.repository;

import com.nhattan.ecommerce.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Modifying
    @Query(value = "UPDATE Product SET status = 'NOT_AVAILABLE' WHERE productID = :productID", nativeQuery = true)
    public void deleteByProductID(@Param("productID") int id);

    @Modifying
    @Query(value = "UPDATE Product SET productName = :productName, description = :description, quantity = :quantity, "
            + "subcategoryID = :subcategoryID WHERE productID = :productID", nativeQuery = true)
    public void updateByProductID(@Param("productName") String productName, @Param("description") String description,
                                  @Param("subcategoryID") int subcategoryID, @Param("quantity") int quantity,
                                  @Param("productID") int productID);

    @Modifying
    @Query(value = "UPDATE Product SET quantity = :newQuantity WHERE productID = :productID", nativeQuery = true)
    void updateQuantityByProductID(@Param("newQuantity") int newQuantity, @Param("productID") int productID);

    Optional<ProductEntity> findByStatusAndProductID(String status, int productID);

    @Query(value = "SELECT productID, productName, description, quantity, point, subcategoryID, status "
            + "FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY Product.productName ASC) AS top_number "
            + "FROM Product WHERE status = 'NOT_AVAILABLE' OR subcategoryID IN (SELECT subcategoryID FROM Subcategory "
            + "WHERE status = 'NOT_AVAILABLE' OR subcategoryID IN (SELECT subcategoryID FROM Category "
            + "WHERE Category.status = 'NOT_AVAILABLE' AND Category.categoryID = Subcategory.categoryID))) AS rs "
            + "WHERE top_number BETWEEN :begin AND :end ", nativeQuery = true)
    List<ProductEntity> findProductNotAvailableOrderByASC(@Param("begin") int begin, @Param("end") int end);

    @Query(value = "SELECT productID, productName, description, quantity, point, subcategoryID, status "
            + "FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY Product.productName DESC ) AS top_number "
            + "FROM Product WHERE status = 'NOT_AVAILABLE' OR subcategoryID IN (SELECT subcategoryID FROM Subcategory "
            + "WHERE status = 'NOT_AVAILABLE' OR subcategoryID IN (SELECT subcategoryID FROM Category "
            + "WHERE Category.status = 'NOT_AVAILABLE' AND Category.categoryID = Subcategory.categoryID))) AS rs "
            + "WHERE top_number BETWEEN :begin AND :end ", nativeQuery = true)
    List<ProductEntity> findProductNotAvailableOrderByDESC(@Param("begin") int begin, @Param("end") int end);

    @Query(value = "SELECT COUNT(1) FROM Product WHERE status = 'NOT_AVAILABLE' OR subcategoryID IN (SELECT subcategoryID FROM Subcategory "
            + "WHERE status = 'NOT_AVAILABLE' OR subcategoryID IN (SELECT subcategoryID FROM Category WHERE Category.status = 'NOT_AVAILABLE' "
            + "AND Category.categoryID = Subcategory.categoryID))", nativeQuery = true)
    int countProductNotAvailable();

    int countByStatusOrSubcategory_StatusOrSubcategory_Category_Status(String status, String subcategoryStatus
            , String categoryStatus);

    @Modifying
    @Query(value = "UPDATE Product SET point = (SELECT SUM(pr.point) FROM ProductRating pr "
            + "WHERE pr.productID = :productID) / ISNULL(NULLIF((SELECT COUNT(1) FROM ProductRating pr "
            + "WHERE pr.productID = :productID), 0), 1) WHERE productID = :productID", nativeQuery = true)
    void updateProductPointByProductID(@Param("productID") int productID);

    @Query(value = "SELECT * FROM Product WHERE productID = :productID AND status = 'AVAILABLE' AND (SELECT Subcategory.status "
            + "FROM Subcategory WHERE Product.subcategoryID = Subcategory.subcategoryID "
            + "AND (SELECT Category.status FROM Category WHERE Category.categoryID = "
            + "Subcategory.categoryID) = 'AVAILABLE') = 'AVAILABLE'", nativeQuery = true)
    Optional<ProductEntity> findOneAvailable(@Param("productID") int productID);

    Optional<ProductEntity> findByProductIDAndStatus(int productID, String status);

    @Modifying
    @Query(value = "UPDATE Product SET status = 'AVAILABLE' WHERE productID = :productID", nativeQuery = true)
    void activityProduct(@Param("productID") int productID);

    @Query(value = "SELECT productID, productName, description, quantity, point, subcategoryID, status "
            + "FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY Product.productName ASC) AS top_number "
            + "FROM Product WHERE status = 'AVAILABLE' AND subcategoryID IN (SELECT s.subcategoryID "
            + "FROM (SELECT subcategoryID, categoryID FROM Subcategory WHERE Subcategory.status = 'AVAILABLE') AS s "
            + "INNER JOIN (SELECT categoryID FROM Category WHERE Category.status = 'AVAILABLE') AS c ON s.categoryID = c.categoryID)) "
            + "AS rs WHERE top_number BETWEEN (:begin) AND (:end)", nativeQuery = true)
    List<ProductEntity> findProductAvailableOrderByASC(@Param("begin") int begin, @Param("end") int end);

    @Query(value = "SELECT productID, productName, description, quantity, point, subcategoryID, status "
            + "FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY Product.productName DESC) AS top_number "
            + "FROM Product WHERE status = 'AVAILABLE' AND subcategoryID IN (SELECT s.subcategoryID "
            + "FROM (SELECT subcategoryID, categoryID FROM Subcategory WHERE Subcategory.status = 'AVAILABLE') AS s "
            + "INNER JOIN (SELECT categoryID FROM Category WHERE Category.status = 'AVAILABLE') AS c ON s.categoryID = c.categoryID)) "
            + "AS rs WHERE top_number BETWEEN (:begin) AND (:end)", nativeQuery = true)
    List<ProductEntity> findProductAvailableOrderByDESC(@Param("begin") int begin, @Param("end") int end);

    @Query(value = "SELECT productID, productName, description, quantity, point, subcategoryID, status "
            + "FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY Product.productName ASC) AS top_number "
            + "FROM Product WHERE status = 'AVAILABLE' AND productName LIKE %:productName% AND subcategoryID IN (SELECT s.subcategoryID "
            + "FROM (SELECT subcategoryID, categoryID FROM Subcategory WHERE Subcategory.status = 'AVAILABLE') AS s "
            + "INNER JOIN (SELECT categoryID FROM Category WHERE Category.status = 'AVAILABLE') AS c ON s.categoryID = c.categoryID)) "
            + "AS rs WHERE top_number BETWEEN :begin AND :end", nativeQuery = true)
    List<ProductEntity> findProductAvailableByProductNameOrderByASC(@Param("begin") int begin, @Param("end") int end, @Param("productName") String productName);

    @Query(value = "SELECT productID, productName, description, quantity, point, subcategoryID, status "
            + "FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY Product.productName DESC) AS top_number "
            + "FROM Product WHERE status = 'AVAILABLE' AND productName LIKE %:productName% AND subcategoryID IN (SELECT s.subcategoryID "
            + "FROM (SELECT subcategoryID, categoryID FROM Subcategory WHERE Subcategory.status = 'AVAILABLE') AS s "
            + "INNER JOIN (SELECT categoryID FROM Category WHERE Category.status = 'AVAILABLE') AS c ON s.categoryID = c.categoryID)) "
            + "AS rs WHERE top_number BETWEEN :begin AND :end", nativeQuery = true)
    List<ProductEntity> findProductAvailableByProductNameOrderByDESC(@Param("begin") int begin, @Param("end") int end, @Param("productName") String productName);

    @Query(value = "SELECT COUNT(1) FROM Product WHERE status = 'AVAILABLE' AND productName LIKE :productName AND subcategoryID " +
            "IN (SELECT s.subcategoryID FROM (SELECT subcategoryID, categoryID FROM Subcategory " +
            "WHERE Subcategory.status = 'AVAILABLE') AS s INNER JOIN (SELECT categoryID FROM Category " +
            "WHERE Category.status = 'AVAILABLE') AS c ON s.categoryID = c.categoryID)", nativeQuery = true)
    int countProductAvailableByProductName(@Param("productName") String productName);

    int countByStatusAndNameAndSubcategory_StatusAndSubcategory_Category_Status(String status, String name
            , String subcategoryStatus, String categoryStatus);

    @Query(value = "SELECT COUNT(1) FROM Product WHERE status = 'AVAILABLE' AND subcategoryID IN (SELECT s.subcategoryID FROM (SELECT subcategoryID, "
            + "categoryID FROM Subcategory WHERE Subcategory.status = 'AVAILABLE') AS s INNER JOIN (SELECT categoryID FROM Category WHERE Category.status = 'AVAILABLE') "
            + "AS c ON s.categoryID = c.categoryID)", nativeQuery = true)
    int countProductAvailable();

    int countByStatusAndSubcategory_StatusAndSubcategory_Category_Status(String status, String subcategoryStatus
            , String categoryStatus);

    @Query(value = "SELECT productID, productName, description, quantity, point, subcategoryID, status "
            + "FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY Product.productName ASC) AS top_number "
            + "FROM Product WHERE subcategoryID = :subcategoryID AND status = 'AVAILABLE' AND subcategoryID IN (SELECT s.subcategoryID "
            + "FROM (SELECT subcategoryID, categoryID FROM Subcategory WHERE Subcategory.status = 'AVAILABLE') AS s "
            + "INNER JOIN (SELECT categoryID FROM Category WHERE Category.status = 'AVAILABLE') AS c ON s.categoryID = c.categoryID)) "
            + "AS rs WHERE top_number BETWEEN :begin AND :end", nativeQuery = true)
    List<ProductEntity> findProductAvailableBySubcategoryOrderByASC(@Param("begin") int begin, @Param("end") int end, @Param("subcategoryID") int subcategoryID);

    @Query(value = "SELECT productID, productName, description, quantity, point, subcategoryID, status "
            + "FROM (SELECT *, ROW_NUMBER() OVER(ORDER BY Product.productName DESC) AS top_number "
            + "FROM Product WHERE subcategoryID = :subcategoryID AND status = 'AVAILABLE' AND subcategoryID IN (SELECT s.subcategoryID "
            + "FROM (SELECT subcategoryID, categoryID FROM Subcategory WHERE Subcategory.status = 'AVAILABLE') AS s "
            + "INNER JOIN (SELECT categoryID FROM Category WHERE Category.status = 'AVAILABLE') AS c ON s.categoryID = c.categoryID)) "
            + "AS rs WHERE top_number BETWEEN :begin AND :end", nativeQuery = true)
    List<ProductEntity> findProductAvailableBySubcategoryOrderByDESC(@Param("begin") int begin, @Param("end") int end, @Param("subcategoryID") int subcategoryID);

    @Query(value = "SELECT COUNT(1) FROM Product WHERE subcategoryID = :subcategoryID AND status = 'AVAILABLE' AND subcategoryID IN (SELECT s.subcategoryID "
            + "FROM (SELECT subcategoryID, categoryID FROM Subcategory WHERE Subcategory.status = 'AVAILABLE') AS s INNER JOIN (SELECT categoryID FROM Category "
            + "WHERE Category.status = 'AVAILABLE') AS c ON s.categoryID = c.categoryID)", nativeQuery = true)
    int countProductAvailableBySubcategory(@Param("subcategoryID") int subcategoryID);

    int countBySubcategory_SubcategoryIDAndStatusAndSubcategory_StatusAndSubcategory_Category_Status(int subcategoryID
            , String status, String subcategoryStatus, String categoryStatus);
}
