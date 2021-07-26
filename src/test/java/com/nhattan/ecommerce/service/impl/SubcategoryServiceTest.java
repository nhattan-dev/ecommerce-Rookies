package com.nhattan.ecommerce.service.impl;

import com.nhattan.ecommerce.dto.SubcategoryDTO;
import com.nhattan.ecommerce.entity.CategoryEntity;
import com.nhattan.ecommerce.entity.SubcategoryEntity;
import com.nhattan.ecommerce.enums.STATUS;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.ICategoryRepository;
import com.nhattan.ecommerce.repository.ISubcategoryRepository;
import org.aspectj.weaver.ast.Not;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SubcategoryServiceTest {

    SubcategoryDTO dto, resultDTO;
    SubcategoryEntity entity;
    List<SubcategoryEntity> entities;

    @Mock
    ISubcategoryRepository subcategoryRepository;

    @Mock
    ICategoryRepository categoryRepository;

    @InjectMocks
    SubcategoryService service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

        dto = new SubcategoryDTO();
        dto.setDescription("setDescription");
        dto.setCategoryID(8);
        dto.setSubcategoryName("setSubcategoryName");
        dto.setSubcategoryID(5);

        entity = new SubcategoryEntity();
        entity.setDescription("setDescription");
        entity.setSubcategoryName("setSubcategoryName");
        entity.setSubcategoryID(5);
        entity.setSubcategoryCode("setsubcategoryname");
        entity.setStatus(STATUS.NOT_AVAILABLE.name());
        CategoryEntity cate = new CategoryEntity();
        cate.setCategoryID(8);
        entity.setCategory(cate);

        resultDTO = new SubcategoryDTO();
        resultDTO.setDescription("setDescription");
        resultDTO.setSubcategoryName("setSubcategoryName");
        resultDTO.setSubcategoryID(5);
        resultDTO.setSubcategoryCode("setsubcategoryname");
        resultDTO.setStatus(STATUS.NOT_AVAILABLE.name());
        resultDTO.setCategoryID(8);

        entities = Arrays.asList(entity,entity,entity);
    }

    @Test
    public void testSaveSubcategory_then_return_dto(){
        when(categoryRepository.existsById(anyInt())).thenReturn(true);
        when(subcategoryRepository.existsSubcategoryBySubcategoryCodeAndSubcategoryIDNot(anyString(), anyInt())).thenReturn(false);
        when(subcategoryRepository.save(any())).thenReturn(entity);

        SubcategoryDTO result = service.saveSubcategory(dto);

        assertEquals(result.getSubcategoryID(), resultDTO.getSubcategoryID());
        assertEquals(result.getSubcategoryName(), resultDTO.getSubcategoryName());
        assertEquals(result.getStatus(), resultDTO.getStatus());
        assertEquals(result.getSubcategoryCode(), resultDTO.getSubcategoryCode());
        assertEquals(result.getDescription(), resultDTO.getDescription());
        assertEquals(result.getCategoryID(), resultDTO.getCategoryID());
    }

    @Test
    public void testSaveSubcategory_when_category_not_found_then_throw_notFoundException(){
        when(categoryRepository.existsById(anyInt())).thenReturn(false);
        NotFoundException exception = assertThrows(NotFoundException.class, ()->service.saveSubcategory(dto));
        assertEquals(exception.getMessage(), "category-not-found");
    }

    @Test
    public void testSaveSubcategory_when_subcategory_already_used_then_throw_ConflictException(){
        when(categoryRepository.existsById(anyInt())).thenReturn(true);
        when(subcategoryRepository.existsSubcategoryBySubcategoryCodeAndSubcategoryIDNot(anyString(), anyInt())).thenReturn(true);
        ConflictException exception = assertThrows(ConflictException.class, ()->service.saveSubcategory(dto));
        assertEquals(exception.getMessage(), "try-another-name");
    }

    @Test
    public void testInvalidateSubcategory_when_subcategory_not_found_then_throw_notFoundException(){
        when(subcategoryRepository.existsById(anyInt())).thenReturn(false);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.invalidateSubcategory(anyInt()));
        assertEquals(exception.getMessage(), "subcategory-not-found");
    }

    @Test
    public void testInvalidateSubcategory_then_return_successfully(){
        when(subcategoryRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(subcategoryRepository).deleteBySubcategoryID(anyInt());

        String result = service.invalidateSubcategory(5);
        assertEquals(result, "successfully");
    }

    @Test
    public void testFindAll_then_return_dto_list(){
        when(subcategoryRepository.findAll()).thenReturn(entities);
        assertEquals(service.findAll().size(), entities.size());
    }

    @Test
    public void testUpdateSubcategory_then_return_dto(){
        when(subcategoryRepository.existsById(anyInt())).thenReturn(true);
        when(categoryRepository.existsById(anyInt())).thenReturn(true);
        when(subcategoryRepository.existsBySubcategoryCodeAndSubcategoryIDNot(anyString(), anyInt())).thenReturn(false);
        when(subcategoryRepository.findById(anyInt())).thenReturn(Optional.of(entity));

        SubcategoryDTO result = service.updateSubcategory(dto);

        assertEquals(result.getCategoryID(), resultDTO.getCategoryID());
        assertEquals(result.getSubcategoryID(), resultDTO.getSubcategoryID());
        assertEquals(result.getSubcategoryName(), resultDTO.getSubcategoryName());
        assertEquals(result.getDescription(), resultDTO.getDescription());
        assertEquals(result.getSubcategoryCode(), resultDTO.getSubcategoryCode());
        assertEquals(result.getStatus(), resultDTO.getStatus());
    }

    @Test
    public void testUpdateSubcategory_when_subcategory_not_exists_then_throw_notFoundException(){
        when(subcategoryRepository.existsById(anyInt())).thenReturn(false);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.updateSubcategory(dto));
        assertEquals(exception.getMessage(), "subcategory-not-found");
    }

    @Test
    public void testUpdateSubcategory_when_category_not_exists_then_throw_notFoundException(){
        when(subcategoryRepository.existsById(anyInt())).thenReturn(true);
        when(categoryRepository.existsById(anyInt())).thenReturn(false);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.updateSubcategory(dto));
        assertEquals(exception.getMessage(), "category-not-found");
    }

    @Test
    public void testUpdateSubcategory_when_subcategoryCode_used_then_throw_conflictException(){
        when(subcategoryRepository.existsById(anyInt())).thenReturn(true);
        when(categoryRepository.existsById(anyInt())).thenReturn(true);
        when(subcategoryRepository.existsBySubcategoryCodeAndSubcategoryIDNot(anyString(), anyInt())).thenReturn(true);
        ConflictException exception = assertThrows(ConflictException.class, () -> service.updateSubcategory(dto));
        assertEquals(exception.getMessage(), "subcategorycode-already-use.-try-another-name");
    }

    @Test
    public void testFindOneSubcategory_then_return_dto(){
        when(subcategoryRepository.findById(anyInt())).thenReturn(Optional.of(entity));
        SubcategoryDTO result = service.findOneSubcategory(anyInt());
        assertEquals(result.getStatus(), resultDTO.getStatus());
        assertEquals(result.getCategoryID(), resultDTO.getCategoryID());
        assertEquals(result.getSubcategoryName(), resultDTO.getSubcategoryName());
        assertEquals(result.getSubcategoryCode(), resultDTO.getSubcategoryCode());
        assertEquals(result.getSubcategoryID(), resultDTO.getSubcategoryID());
        assertEquals(result.getDescription(), resultDTO.getDescription());
    }

    @Test
    public void testFindOneSubcategoryAvailable_then_return_dto(){
        when(subcategoryRepository.findOneByAvailable(anyInt())).thenReturn(Optional.of(entity));
        SubcategoryDTO result = service.findOneSubcategoryAvailable(anyInt());
        assertEquals(result.getStatus(), resultDTO.getStatus());
        assertEquals(result.getCategoryID(), resultDTO.getCategoryID());
        assertEquals(result.getSubcategoryName(), resultDTO.getSubcategoryName());
        assertEquals(result.getSubcategoryCode(), resultDTO.getSubcategoryCode());
        assertEquals(result.getSubcategoryID(), resultDTO.getSubcategoryID());
        assertEquals(result.getDescription(), resultDTO.getDescription());
    }

    @Test
    public void testFindSubcategoryAvailable_then_return_dto_list(){
        when(subcategoryRepository.findByStatusAndCategory_Status(anyString(), anyString()))
                .thenReturn(entities);
        List<SubcategoryDTO> results = service.findSubcategoryAvailable();
        assertEquals(results.size(), entities.size());
    }

    @Test
    public void testFindSubcategoryNotAvailable_then_return_dto_list(){
        when(subcategoryRepository.findByStatusOrCategory_Status(anyString(), anyString())).thenReturn(entities);
        List<SubcategoryDTO> results = service.findSubcategoryNotAvailable();
        assertEquals(results.size(), entities.size());
    }

    @Test
    public void testActivitySubcategory_when_subcategory_not_exists_then_throw_not_found(){
        when(subcategoryRepository.findById(anyInt())).thenReturn(Optional.empty());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.activitySubcategory(anyInt()));
        assertEquals(exception.getMessage(), "subcategory-not-found");
    }

    @Test
    public void testActivitySubcategory_then_return_successfully(){
        when(subcategoryRepository.findById(anyInt())).thenReturn(Optional.of(entity));
        doNothing().when(subcategoryRepository).activitySubcategory(anyInt());

        String result = service.activitySubcategory(5);
        assertEquals(result, "successfully");
    }
}
