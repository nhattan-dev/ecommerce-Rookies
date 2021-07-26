package com.nhattan.ecommerce.repository;

import com.nhattan.ecommerce.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Integer> {
	@Modifying
	@Query(value = "UPDATE Category SET status = 'NOT_AVAILABLE' WHERE categoryID = :categoryID", nativeQuery = true)
	void deleteByCategoryID(@Param("categoryID") int id);

//	@Query(value = "SELECT * FROM Category c WHERE status = AVAILABLE", nativeQuery = true)
//	List<CategoryEntity> findAllValid();

	List<CategoryEntity> findByStatus(String status);


	Boolean existsCategoryByCategoryCodeAndCategoryIDNot(String categoryCode, int categoryID);

	Boolean existsCategoryByCategoryCode(String categoryCode);

	Boolean existsCategoryByCategoryID(int categoryID);
	
	@Modifying
	@Query(value = "UPDATE Category SET categoryName = :categoryName, categoryCode = :categoryCode, "
			+ "description = :description, imagePath = :imagePath WHERE categoryID = :categoryID", nativeQuery = true)
	void updateByCategoryID(@Param("categoryName") String categoryName,
							@Param("categoryCode") String categoryCode, @Param("description") String description,
							@Param("imagePath") String imagePath, @Param("categoryID") int categoryID);

	@Modifying
	@Query(value = "UPDATE Category SET status = 'AVAILABLE' WHERE categoryID = :categoryID", nativeQuery = true)
	void activityCategory(@Param("categoryID") int categoryID);
}
