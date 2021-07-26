package com.nhattan.ecommerce.repository;

import com.nhattan.ecommerce.entity.SubcategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ISubcategoryRepository extends JpaRepository<SubcategoryEntity, Integer> {
    @Modifying
    @Query(value = "UPDATE Subcategory SET status = 'NOT_AVAILABLE' WHERE subcategoryID = :subcategoryID", nativeQuery = true)
    void deleteBySubcategoryID(@Param("subcategoryID") int id);

    @Modifying
    @Query(value = "UPDATE Subcategory SET subcategoryName = :subcategoryName, subcategoryCode = :subcategoryCode, "
            + "description = :description, categoryID = :categoryID WHERE subcategoryID = :subcategoryID", nativeQuery = true)
    void updateBySubcategoryID(@Param("subcategoryName") String subcategoryName,
                               @Param("subcategoryCode") String subcategoryCode, @Param("description") String description,
                               @Param("categoryID") int categoryID, @Param("subcategoryID") int subcategoryID);

    Boolean existsBySubcategoryCodeAndSubcategoryIDNot(String subcategoryCode, int subcategoryID);

    @Query(value = "SELECT TOP 1 * FROM Subcategory WHERE status = 'AVAILABLE' AND subcategoryID = :subcategoryID AND " +
            "(SELECT Category.status FROM Category WHERE Category.categoryID = Subcategory.categoryID) = 'AVAILABLE'"
            , nativeQuery = true)
    Optional<SubcategoryEntity> findOneByAvailable(@Param("subcategoryID") int subcategoryID);

    Optional<SubcategoryEntity> findByStatusAndSubcategoryIDAndCategory_Status(String status, int subcategoryID
            , String categoryStatus);

    List<SubcategoryEntity> findByStatusOrCategory_Status(String status, String categoryStatus);

    List<SubcategoryEntity> findByStatusAndCategory_Status(String status, String categoryStatus);

    @Modifying
    @Query(value = "UPDATE Subcategory SET status = 'AVAILABLE' WHERE subcategoryID = :subcategoryID", nativeQuery = true)
    void activitySubcategory(@Param("subcategoryID") int subcategoryID);

    Boolean existsSubcategoryBySubcategoryCodeAndSubcategoryIDNot(String subcategoryCode, int subcategoryID);
}
