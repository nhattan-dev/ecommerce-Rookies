package com.nhattan.ecommerce.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nhattan.ecommerce.dto.CategoryDTO;
import com.nhattan.ecommerce.entity.CategoryEntity;
import com.nhattan.ecommerce.repository.ICategoryRepository;
import com.nhattan.ecommerce.service.ICategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceTest {

	@MockBean
	private ICategoryRepository categoryRepository;

	@Autowired
	private ICategoryService categoryService;

	@Test
	void testSaveCategory() {
		// Give
		CategoryDTO inputDTO = new CategoryDTO("đồng Hồ hiệu", "không mô tả", "imagePath");
		CategoryDTO assertDTO = new CategoryDTO(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả", "imagePath");
		CategoryEntity outputCategory = new CategoryEntity(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả",
				"imagePath", 1);

		//when
		CategoryEntity entity = CategoryDTO.toEntity(inputDTO);
		Mockito.when(categoryRepository.save(entity)).thenReturn(outputCategory);

		// Then
		CategoryDTO result = categoryService.saveCategory(inputDTO);
		assertTrue(result.equals(assertDTO));
	}

	@Test
	void testFindAll() {
		// give
		List<CategoryEntity> results = Arrays.asList(
				new CategoryEntity(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả", "imagePath", 1),
				new CategoryEntity(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả", "imagePath", 0),
				new CategoryEntity(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả", "imagePath", 0),
				new CategoryEntity(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả", "imagePath", 1),
				new CategoryEntity(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả", "imagePath", 1));
		//when
		when(categoryRepository.findAll()).thenReturn(results);
		
		//then
		assertThat(categoryService.findAll()).hasSize(3);
	}

	@Test
	void testFindCategoryAvailable() {
//		// give
		List<CategoryEntity> results = Arrays.asList(
				new CategoryEntity(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả", "imagePath", 1),
				new CategoryEntity(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả", "imagePath", 0),
				new CategoryEntity(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả", "imagePath", 0),
				new CategoryEntity(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả", "imagePath", 1),
				new CategoryEntity(5, "dong-ho-hieu", "đồng Hồ hiệu", "không mô tả", "imagePath", 1));
		//when
		int NotDeleteValue = 0;
		when(categoryRepository.findByDeleted(NotDeleteValue)).thenReturn(results);
		
		//then
		System.out.println(categoryService.findCategoryAvailable().size());
		assertThat(categoryService.findCategoryAvailable()).hasSize(5);
	}

	@Test
	void testFindCategoryNotAvailable() {
//		fail("Not yet implemented");
	}

	@Test
	void testInvalidateCategory() {
//		fail("Not yet implemented");
	}

	@Test
	void testUpdateCategory() {
//		fail("Not yet implemented");
	}

	@Test
	void testReactivityCategory() {
//		fail("Not yet implemented");
	}

}
