package com.nhattan.ecommerce.controller;

import com.nhattan.ecommerce.dto.SubcategoryDTO;
import com.nhattan.ecommerce.entity.CategoryEntity;
import com.nhattan.ecommerce.entity.SubcategoryEntity;
import com.nhattan.ecommerce.enums.STATUS;
import com.nhattan.ecommerce.repository.ISubcategoryRepository;
import com.nhattan.ecommerce.service.impl.SubcategoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SubcategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubcategoryService subService;

    @MockBean
    private ISubcategoryRepository subRepo;

    private List<SubcategoryEntity> subList = Arrays.asList(new SubcategoryEntity(1, "subcode", "subCode", "description"
            , STATUS.NOT_AVAILABLE.name(), new CategoryEntity())
            , new SubcategoryEntity(), new SubcategoryEntity(), new SubcategoryEntity());

    private SubcategoryDTO dto = new SubcategoryDTO(0, "", "cate1", "description"
            , 5, STATUS.NOT_AVAILABLE.name());

    @Test
    public void showSubSubcategory() {

    }

    @Test
    public void showSubSubcategoryAvailable() {
    }

    @Test
    public void showOneSubSubcategory() {
    }

    @Test
    public void testShowSubSubcategoryAvailable() {
    }

    @Test
    public void createSubSubcategory() {
    }

    @Test
    public void updateSubSubcategory() {
    }

    @Test
    public void activitySubSubcategory() {
    }

    @Test
    public void invalidateSubSubcategory() {
    }
}