package com.nhattan.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhattan.ecommerce.request.CreateSubcategoryRequest;
import com.nhattan.ecommerce.request.UpdateSubcategoryRequest;
import com.nhattan.ecommerce.response.ReadSubcagoryResponse;
import com.nhattan.ecommerce.service.ISubcategoryService;

@RestController
public class SubcategoryController {
	@Autowired
	private ISubcategoryService subcategoryService;

	@GetMapping(value = "/subcategory")
	public List<ReadSubcagoryResponse> showAllSubcategory() {
		return subcategoryService.findAll();
	}

	@GetMapping(value = "/subcategory-valid")
	public List<ReadSubcagoryResponse> showSubcategoryValid() {
		return subcategoryService.findSubcategoryValid();
	}
	
	@GetMapping(value = "/subcategory/{subcategoryID}")
	public ReadSubcagoryResponse showSubcategory(@PathVariable int subcategoryID) {
		return subcategoryService.findOne(subcategoryID);
	}

	@PostMapping(value = "/subcategory")
	public ReadSubcagoryResponse createSubcategory(@Valid @RequestBody CreateSubcategoryRequest subcategoryRequest) {
		return subcategoryService.save(subcategoryRequest);
	}

	@PutMapping(value = "/subcategory/{id}")
	public ReadSubcagoryResponse updateSubcategory(@Valid @RequestBody UpdateSubcategoryRequest subcategoryRequest, @PathVariable int id) {
		subcategoryRequest.setSubcategoryID(id);
		return subcategoryService.update(subcategoryRequest);
	}

	@DeleteMapping(value = "/subcategory/{id}")
	public void deleteSubcategory(@PathVariable int id) {
		subcategoryService.delete(new Integer(id));
	}
}
