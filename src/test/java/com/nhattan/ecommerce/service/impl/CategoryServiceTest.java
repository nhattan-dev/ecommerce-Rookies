package com.nhattan.ecommerce.service.impl;

import com.nhattan.ecommerce.dto.CategoryDTO;
import com.nhattan.ecommerce.entity.CategoryEntity;
import com.nhattan.ecommerce.enums.STATUS;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.ICategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    List<CategoryEntity> entityList;
    List<CategoryDTO> dtoList;
    CategoryDTO dto, resultDTO;
    CategoryEntity entity;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        entityList = Arrays.asList(
                new CategoryEntity(),
                new CategoryEntity(),
                new CategoryEntity());
        dtoList = Arrays.asList(
                new CategoryDTO(),
                new CategoryDTO(),
                new CategoryDTO());

        entity = new CategoryEntity();
        entity.setImagePath("setImagePath");
        entity.setCategoryID(567);
        entity.setDescription("setDescription");
        entity.setCategoryName("setCategoryName");
        entity.setCategoryCode("setcategoryname");
        entity.setStatus(STATUS.NOT_AVAILABLE.name());

        resultDTO = new CategoryDTO();
        resultDTO.setImagePath("setImagePath");
        resultDTO.setCategoryID(567);
        resultDTO.setDescription("setDescription");
        resultDTO.setCategoryName("setCategoryName");
        resultDTO.setCategoryCode("setcategoryname");
        resultDTO.setStatus(STATUS.NOT_AVAILABLE.name());

        dto = new CategoryDTO();
        dto.setImagePath("setImagePath");
        dto.setCategoryID(567);
        dto.setDescription("setDescription");
        dto.setCategoryName("setCategoryName");
    }

    @Mock
    private ICategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void TestFindAll_then_return_dto_list() {
        Mockito.when(categoryRepository.findAll()).thenReturn(entityList);
        Assertions.assertTrue(categoryService.findAll().size() == entityList.size());
    }

    @Test
    public void testSaveCategory_when_CategoryCode_Has_Been_Used_Then_throw_exception() {
        // Give
        CategoryDTO inputDTO = new CategoryDTO();
        inputDTO.setCategoryName("đồng Hồ hiệu");
        inputDTO.setDescription("không mô tả");
        inputDTO.setImagePath("imagePath");

        when(categoryRepository.existsCategoryByCategoryCode(anyString())).thenReturn(true);
        ConflictException exception = assertThrows(ConflictException.class, () -> categoryService.saveCategory(inputDTO));
        assertEquals(exception.getMessage(), "categorycode-already-exists");
    }

    @Test
    public void testSaveCategory_then_return_dto() {
        // Give
        CategoryDTO inputDTO = new CategoryDTO();
        inputDTO.setCategoryName("đồng Hồ hiệu");
        inputDTO.setDescription("không mô tả");
        inputDTO.setImagePath("imagePath");

        CategoryDTO assertDTO = new CategoryDTO();//5, , , , , );
        assertDTO.setCategoryID(5);
        assertDTO.setCategoryCode("dong-ho-hieu");
        assertDTO.setCategoryName("đồng Hồ hiệu");
        assertDTO.setDescription("không mô tả");
        assertDTO.setImagePath("imagePath");
        assertDTO.setStatus(STATUS.NOT_AVAILABLE.name());

        CategoryEntity outputCategory = new CategoryEntity();
        outputCategory.setCategoryID(5);
        outputCategory.setCategoryCode("dong-ho-hieu");
        outputCategory.setCategoryName("đồng Hồ hiệu");
        outputCategory.setDescription("không mô tả");
        outputCategory.setImagePath("imagePath");
        outputCategory.setStatus(STATUS.NOT_AVAILABLE.name());

        when(categoryRepository.save(any())).thenReturn(outputCategory);
        when(categoryRepository.existsCategoryByCategoryCode(anyString())).thenReturn(false);

        CategoryDTO result = categoryService.saveCategory(inputDTO);

        assertTrue(assertDTO.getCategoryID() == result.getCategoryID());
        assertTrue(assertDTO.getImagePath() == result.getImagePath());
        assertTrue(assertDTO.getCategoryName() == result.getCategoryName());
        assertTrue(assertDTO.getStatus() == result.getStatus());
        assertTrue(assertDTO.getDescription() == result.getDescription());
        assertTrue(assertDTO.getCategoryCode() == result.getCategoryCode());
    }

    @Test
    public void testInvalidateCategory_when_CategoryID_not_found() {
        when(categoryRepository.existsById(anyInt())).thenReturn(false);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> categoryService.invalidateCategory(anyInt()));
        assertTrue(exception.getMessage().equalsIgnoreCase("category-not-found"));
    }

    @Test
    public void testInvalidateCategory() {
        when(categoryRepository.existsById(anyInt())).thenReturn(true);
        categoryService.invalidateCategory(anyInt());
    }

    @Test
    public void testFindOne_when_not_found_then_throw_404(){
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.empty());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> categoryService.findOne(anyInt()));
        assertTrue(exception.getMessage().equalsIgnoreCase("category-not-found"));
    }

    @Test
    public void testFindOne_then_return_one(){
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(entity));

        CategoryDTO result = categoryService.findOne(5);

        assertEquals(result.getCategoryID(), entity.getCategoryID());
        assertEquals(result.getCategoryCode(), entity.getCategoryCode());
        assertEquals(result.getCategoryName(), entity.getCategoryName());
        assertEquals(result.getStatus(), entity.getStatus());
        assertEquals(result.getDescription(), entity.getDescription());
        assertEquals(result.getImagePath(), entity.getImagePath());
    }

    @Test
    public void testFindCategoryNotAvailable_then_return_list() {
        when(categoryRepository.findByStatus(anyString())).thenReturn(entityList);
        List<CategoryDTO> result = categoryService.findCategoryAvailable();
        assertThat(result).hasSize(entityList.size());
    }

    @Test
    public void testFindCategoryAvailable_then_return_list() {
        when(categoryRepository.findByStatus(anyString())).thenReturn(entityList);
        List<CategoryDTO> result = categoryService.findCategoryAvailable();
        assertThat(result).hasSize(entityList.size());
    }

    @Test
    public void testUpdateCategory_then_return_dto() {
        when(categoryRepository.existsById(anyInt())).thenReturn(true);
        when(categoryRepository.existsCategoryByCategoryCodeAndCategoryIDNot(anyString(), anyInt())).thenReturn(false);
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(entity));

        CategoryDTO result = categoryService.updateCategory(dto);

        assertTrue(result.getCategoryCode() == resultDTO.getCategoryCode());
        assertTrue(result.getCategoryName() == resultDTO.getCategoryName());
        assertTrue(result.getDescription() == resultDTO.getDescription());
        assertTrue(result.getStatus() == resultDTO.getStatus());
        assertTrue(result.getImagePath() == resultDTO.getImagePath());
        assertTrue(result.getCategoryID() == resultDTO.getCategoryID());
    }

    @Test
    public void testUpdateCategory_when_category_not_exists_then_throw_exception() {
        when(categoryRepository.existsById(anyInt())).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> categoryService.updateCategory(dto));
        assertTrue(exception.getMessage() == "category-not-found");
    }

    @Test
    public void testUpdateCategory_when_categorycode_already_used_then_throw_exception() {
        when(categoryRepository.existsById(anyInt())).thenReturn(true);
        when(categoryRepository.existsCategoryByCategoryCodeAndCategoryIDNot(anyString(), anyInt())).thenReturn(true);

        ConflictException exception = assertThrows(ConflictException.class, () -> categoryService.updateCategory(dto));
        assertTrue(exception.getMessage() == "try-another-name");
    }

    @Test
    public void testActivityCategory_then_return_successfully() {
        when(categoryRepository.existsById(anyInt())).thenReturn(true);
        assertTrue(categoryService.activityCategory(anyInt()) == "successfully");
    }

    @Test
    public void testActivityCategory_when_category_not_found_then_return_exception() {
        when(categoryRepository.existsById(anyInt())).thenReturn(false);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> categoryService.activityCategory(anyInt()));
        assertTrue(exception.getMessage() == "category_not-found");
    }
}
