package com.nhattan.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhattan.ecommerce.entity.SubcategoryEntity;

public interface ISubcategoryRepository extends JpaRepository<SubcategoryEntity, Integer> {
	@Modifying
	@Query(value = "UPDATE Subcategory SET deleted = 1 WHERE subcategoryID = :subcategoryID", nativeQuery = true)
	public void deleteBySubcategoryID(@Param("subcategoryID") int id);

	@Modifying
	@Query(value = "UPDATE Subcategory SET subcategoryName = :subcategoryName, subcategoryCode = :subcategoryCode, "
			+ "description = :description, categoryID = :categoryID WHERE subcategoryID = :subcategoryID", nativeQuery = true)
	public void updateBySubcategoryID(@Param("subcategoryName") String subcategoryName,
			@Param("subcategoryCode") String subcategoryCode, @Param("description") String description,
			@Param("categoryID") int categoryID, @Param("subcategoryID") int subcategoryID);

	@Query(value = "SELECT *  from Subcategory s WHERE s.deleted = :value AND (SELECT c.deleted FROM Category c WHERE c.categoryID = s.categoryID) = :value", nativeQuery = true)
	public List<SubcategoryEntity> findByDeleted(@Param("value") int value);

	public Boolean existsSubcategoryBySubcategoryCode(String subcategoryCode);
	
	public Boolean existsSubcategoryBySubcategoryID(int subcategoryID);
}
