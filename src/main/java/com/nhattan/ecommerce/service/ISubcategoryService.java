package com.nhattan.ecommerce.service;

import com.nhattan.ecommerce.dto.SubcategoryDTO;

import java.util.List;

public interface  ISubcategoryService {
	SubcategoryDTO saveSubcategory(SubcategoryDTO subcategoryDTO);

	SubcategoryDTO updateSubcategory(SubcategoryDTO subcategoryDTO);

	String invalidateSubcategory(Integer id);

	List<SubcategoryDTO> findAll();

	List<SubcategoryDTO> findSubcategoryAvailable();

	List<SubcategoryDTO> findSubcategoryNotAvailable();

	SubcategoryDTO findOneSubcategory(int subcategoryID);

	SubcategoryDTO findOneSubcategoryAvailable(int subcategoryID);

	String activitySubcategory(int subcategoryID);
}
