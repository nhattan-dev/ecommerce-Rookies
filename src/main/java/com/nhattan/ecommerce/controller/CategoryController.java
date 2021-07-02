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

import com.nhattan.ecommerce.request.CreateCategoryRequest;
import com.nhattan.ecommerce.request.UpdateCategoryRequest;
import com.nhattan.ecommerce.response.CategoryResponse;
import com.nhattan.ecommerce.service.ICategoryService;

@RestController
//@RequestMapping("/rest")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@GetMapping(value = "/category")
	public List<CategoryResponse> showCategory() {
		return categoryService.findAll();
	}

	@GetMapping(value = "/category-valid")
	public List<CategoryResponse> showCategoryValid() {
		return categoryService.findAllValid();
	}

	@PostMapping(value = "/category")
	public CategoryResponse createCategory(@Valid @RequestBody CreateCategoryRequest request) {
		return categoryService.save(request);
	}

	@PutMapping(value = "/category/{id}")
	public CategoryResponse updateCategory(@Valid @RequestBody UpdateCategoryRequest request, @PathVariable int id) {
//		request.setCategoryID(id);
		return categoryService.update(request);
	}

	@DeleteMapping(value = "/category/{id}")
	public void deleteCategory(@PathVariable int id) {
		categoryService.delete(new Integer(id));
	}
}
