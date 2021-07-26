package com.nhattan.ecommerce.controller;

import com.nhattan.ecommerce.dto.CategoryDTO;
import com.nhattan.ecommerce.exception.BadRequestException;
import com.nhattan.ecommerce.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping(value = "/admin/category")
    public ResponseEntity<List<CategoryDTO>> showCategory(
            @RequestParam(name = "key", required = false, defaultValue = "all") String key) {
        String showAllValue = "all";
        String showNotAvailableValue = "notavailable";
        if (key.equalsIgnoreCase(showNotAvailableValue))
            return ResponseEntity.ok().body(categoryService.findCategoryNotAvailable());
        else
            return ResponseEntity.ok().body(categoryService.findAll());
    }

    @GetMapping(value = "/admin/category/{id}")
    public ResponseEntity<CategoryDTO> ShowOne(@PathVariable int id) {
        return ResponseEntity.ok().body(categoryService.findOne(id));
    }

    @GetMapping(value = "/public/category")
    public ResponseEntity<List<CategoryDTO>> showCategoryAvailable() {
        return ResponseEntity.ok().body(categoryService.findCategoryAvailable());
    }

    @PostMapping(value = "/admin/category")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO request) {
        return ResponseEntity.ok().body(categoryService.saveCategory(request));
    }

    @PutMapping(value = "/admin/category/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO request, @PathVariable int id) {
        request.setCategoryID(id);
        return ResponseEntity.ok().body(categoryService.updateCategory(request));
    }

    @PatchMapping(value = "admin/category/{categoryID}/activity")
    public ResponseEntity<String> activityCategory(@PathVariable("categoryID") int categoryID) {
        return ResponseEntity.ok().body(categoryService.activityCategory(categoryID));
    }

    @DeleteMapping(value = "/admin/category/{id}")
    public ResponseEntity invalidateCategory(@PathVariable int id) {
        categoryService.invalidateCategory(id);
        return ResponseEntity.ok().build();
    }
}
