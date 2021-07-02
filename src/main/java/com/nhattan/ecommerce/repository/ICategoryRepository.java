package com.nhattan.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhattan.ecommerce.entity.CategoryEntity;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Integer> {
	@Modifying
	@Query(value = "UPDATE Category SET deleted = 1 WHERE categoryID = :categoryID", nativeQuery = true)
	public void deleteByCategoryID(@Param("categoryID") int id);

	@Query(value = "SELECT * FROM Category c WHERE deleted = 0", nativeQuery = true)
	public List<CategoryEntity> findAllValid();

	public List<CategoryEntity> findByDeleted(int value);

	public Boolean existsCategoryByCategoryCodeAndCategoryIDNot(String categoryCode, int categoryID);

	public Boolean existsCategoryByCategoryCode(String categoryCode);

	public Boolean existsCategoryByCategoryID(int categoryID);

	@Modifying
	@Query(value = "UPDATE Category SET categoryName = :categoryName, categoryCode = :categoryCode, "
			+ "description = :description, imagePath = :imagePath WHERE categoryID = :categoryID", nativeQuery = true)
	public void updateByCategoryID(@Param("categoryName") String categoryName,
			@Param("categoryCode") String categoryCode, @Param("description") String description,
			@Param("imagePath") String imagePath, @Param("categoryID") int categoryID);
}
