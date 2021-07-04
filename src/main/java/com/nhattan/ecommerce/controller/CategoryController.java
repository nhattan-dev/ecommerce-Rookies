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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhattan.ecommerce.dto.CategoryDTO;
import com.nhattan.ecommerce.service.ICategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@GetMapping(value = "/admin/category")
	public List<CategoryDTO> showCategory() {
		return categoryService.findAll();
	}

	@GetMapping(value = "/public/category-valid")
	public List<CategoryDTO> showCategoryValid() {
		return categoryService.findAllValid();
	}

	@PostMapping(value = "/admin/category")
	public CategoryDTO createCategory(@Valid @RequestBody CategoryDTO request) {
		return categoryService.save(request);
	}

	@PutMapping(value = "/admin/category/{id}")
	public CategoryDTO updateCategory(@Valid @RequestBody CategoryDTO request, @PathVariable int id) {
		request.setCategoryID(id);
		return categoryService.update(request);
	}

	@DeleteMapping(value = "/admin/category/{id}")
	public void deleteCategory(@PathVariable int id) {
		categoryService.delete(new Integer(id));
	}
}
