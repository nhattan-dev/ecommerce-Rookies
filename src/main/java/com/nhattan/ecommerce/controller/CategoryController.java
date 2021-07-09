package com.nhattan.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhattan.ecommerce.dto.CategoryDTO;
import com.nhattan.ecommerce.service.ICategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@GetMapping(value = "/admin/category")
	public List<CategoryDTO> showCategory(
			@RequestParam(name = "key", required = false, defaultValue = "all") String key) {
		String showAllValue = "all";
		String showNotAvailableValue = "notavailable";
		if (key.equalsIgnoreCase(showAllValue))
			return categoryService.findAll();
		else if (key.equalsIgnoreCase(showNotAvailableValue))
			return categoryService.findCategoryNotAvailable();
		else
			return null;
	}

	@GetMapping(value = "/public/category")
	public List<CategoryDTO> showCategoryAvailable() {
		return categoryService.findCategoryAvailable();
	}

	@PostMapping(value = "/admin/category")
	public CategoryDTO createCategory(@Valid @RequestBody CategoryDTO request) {
		return categoryService.saveCategory(request);
	}

	@PutMapping(value = "/admin/category/{id}")
	public CategoryDTO updateCategory(@Valid @RequestBody CategoryDTO request, @PathVariable int id) {
		request.setCategoryID(id);
		return categoryService.updateCategory(request);
	}

	@PatchMapping(value = "admin/category/{categoryID}/reactivity")
	public String reactivityCategory(@PathVariable("categoryID") int categoryID) {
		return categoryService.reactivityCategory(categoryID);
	}

	@DeleteMapping(value = "/admin/category/{id}")
	public void invalidateCategory(@PathVariable int id) {
		categoryService.invalidateCategory(new Integer(id));
	}
}
