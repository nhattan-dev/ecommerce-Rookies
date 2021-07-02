package com.nhattan.ecommerce.service;

import java.util.List;

import com.nhattan.ecommerce.request.CreateCategoryRequest;
import com.nhattan.ecommerce.request.UpdateCategoryRequest;
import com.nhattan.ecommerce.response.CategoryResponse;


public interface ICategoryService {
	CategoryResponse save(CreateCategoryRequest categoryRequest);
	CategoryResponse update(UpdateCategoryRequest categoryRequest);
	void delete(Integer id);
	List<CategoryResponse> findAll();
	List<CategoryResponse> findAllValid();
}
