package com.nhattan.ecommerce.service;

import java.util.List;

import com.nhattan.ecommerce.dto.CategoryDTO;

public interface ICategoryService {
	CategoryDTO saveCategory(CategoryDTO category);

	CategoryDTO updateCategory(CategoryDTO category);

	void invalidateCategory(Integer id);

	List<CategoryDTO> findAll();

	List<CategoryDTO> findCategoryNotAvailable();

	List<CategoryDTO> findCategoryAvailable();
	
	String reactivityCategory(int categoryID);
}
