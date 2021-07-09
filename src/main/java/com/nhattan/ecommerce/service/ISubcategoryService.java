package com.nhattan.ecommerce.service;

import java.util.List;

import com.nhattan.ecommerce.dto.SubcategoryDTO;

public interface ISubcategoryService {
	SubcategoryDTO saveSubcategory(SubcategoryDTO subcategoryDTO);

	SubcategoryDTO updateSubcategory(SubcategoryDTO subcategoryDTO);

	void invalidateSubcategory(Integer id);

	List<SubcategoryDTO> findAll();

	List<SubcategoryDTO> findSubcategoryAvailable();

	List<SubcategoryDTO> findSubcategoryNotAvailable();

	SubcategoryDTO findOneSubcategory(int subcategoryID);

	SubcategoryDTO findOneSubcategoryAvailable(int subcategoryID);

	String reactivitySubcategory(int subcategoryID);
}
