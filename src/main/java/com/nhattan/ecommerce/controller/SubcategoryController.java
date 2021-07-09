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

import com.nhattan.ecommerce.dto.SubcategoryDTO;
import com.nhattan.ecommerce.service.ISubcategoryService;

@RestController
@RequestMapping("/api")
public class SubcategoryController {
	@Autowired
	private ISubcategoryService subcategoryService;

	@GetMapping(value = "/admin/subcategory")
	public List<SubcategoryDTO> showSubcategory(
			@RequestParam(name = "key", required = false, defaultValue = "all") String key) {
		String showAllValue = "all";
		String showNotAvailableValue = "notavailable";
		if (key.equalsIgnoreCase(showAllValue))
			return subcategoryService.findAll();
		else if (key.equalsIgnoreCase(showNotAvailableValue))
			return subcategoryService.findSubcategoryNotAvailable();
		else
			return null;
	}

	@GetMapping(value = "/public/subcategory")
	public List<SubcategoryDTO> showSubcategoryAvailable() {
		return subcategoryService.findSubcategoryAvailable();
	}

	@GetMapping(value = "/admin/subcategory/{subcategoryID}")
	public SubcategoryDTO showOneSubcategory(@PathVariable int subcategoryID) {
		return subcategoryService.findOneSubcategory(subcategoryID);
	}

	@GetMapping(value = "/public/subcategory/{subcategoryID}")
	public SubcategoryDTO showSubcategoryAvailable(@PathVariable int subcategoryID) {
		return subcategoryService.findOneSubcategoryAvailable(subcategoryID);
	}

	@PostMapping(value = "/admin/subcategory")
	public SubcategoryDTO createSubcategory(@Valid @RequestBody SubcategoryDTO subcategoryRequest) {
		return subcategoryService.saveSubcategory(subcategoryRequest);
	}

	@PutMapping(value = "/admin/subcategory/{id}")
	public SubcategoryDTO updateSubcategory(@Valid @RequestBody SubcategoryDTO subcategoryRequest,
			@PathVariable int id) {
		subcategoryRequest.setSubcategoryID(id);
		return subcategoryService.updateSubcategory(subcategoryRequest);
	}
	
	@PatchMapping(value = "admin/subcategory/{subcategoryID}/reactivity")
	public String reactivitySubcategory(@PathVariable("subcategoryID") int subcategoryID) {
		return subcategoryService.reactivitySubcategory(subcategoryID);
	}

	@DeleteMapping(value = "/admin/subcategory/{id}")
	public void invalidateSubcategory(@PathVariable int id) {
		subcategoryService.invalidateSubcategory(new Integer(id));
	}
}
