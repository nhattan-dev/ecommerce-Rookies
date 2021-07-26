package com.nhattan.ecommerce.controller;

import com.nhattan.ecommerce.dto.SubcategoryDTO;
import com.nhattan.ecommerce.exception.BadRequestException;
import com.nhattan.ecommerce.service.ISubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
			throw new BadRequestException("bad_key");
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
			@PathVariable("id") int id) {
		subcategoryRequest.setSubcategoryID(id);
		return subcategoryService.updateSubcategory(subcategoryRequest);
	}
	
	@PatchMapping(value = "admin/subcategory/{subcategoryID}/activity")
	public String activitySubcategory(@PathVariable("subcategoryID") int subcategoryID) {
		return subcategoryService.activitySubcategory(subcategoryID);
	}

	@DeleteMapping(value = "/admin/subcategory/{id}")
	public void invalidateSubcategory(@PathVariable int id) {
		subcategoryService.invalidateSubcategory(new Integer(id));
	}
}
