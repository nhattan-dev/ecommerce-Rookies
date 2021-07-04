package com.nhattan.ecommerce.service;

import java.util.List;

import com.nhattan.ecommerce.request.CreateSubcategoryRequest;
import com.nhattan.ecommerce.request.UpdateSubcategoryRequest;
import com.nhattan.ecommerce.response.ReadSubcagoryResponse;

public interface ISubcategoryService {
	ReadSubcagoryResponse save(CreateSubcategoryRequest subcategoryRequest);
	ReadSubcagoryResponse update(UpdateSubcategoryRequest subcategoryRequest);
	void delete(Integer id);
	List<ReadSubcagoryResponse> findAll();
	List<ReadSubcagoryResponse> findSubcategoryValid();
	ReadSubcagoryResponse findOne(int subcategoryID);
}
