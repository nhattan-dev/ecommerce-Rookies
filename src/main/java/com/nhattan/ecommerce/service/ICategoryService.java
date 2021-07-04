package com.nhattan.ecommerce.service;

import java.util.List;

import com.nhattan.ecommerce.dto.CategoryDTO;

public interface ICategoryService {
//	CategoryResponse save(CreateCategoryRequest categoryRequest);
//	CategoryResponse update(UpdateCategoryRequest categoryRequest);
//	void delete(Integer id);
//	List<CategoryResponse> findAll();
//	List<CategoryResponse> findAllValid();
	CategoryDTO save(CategoryDTO category);

	CategoryDTO update(CategoryDTO category);

	void delete(Integer id);

	List<CategoryDTO> findAll();

	List<CategoryDTO> findAllValid();
}
