package com.nhattan.ecommerce.service;

import com.nhattan.ecommerce.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
	CategoryDTO saveCategory(CategoryDTO category);

	CategoryDTO updateCategory(CategoryDTO category);

	void invalidateCategory(Integer id);

	List<CategoryDTO> findAll();

	CategoryDTO findOne(int categoryID);

	List<CategoryDTO> findCategoryNotAvailable();

	List<CategoryDTO> findCategoryAvailable();
	
	String activityCategory(int categoryID);
}
