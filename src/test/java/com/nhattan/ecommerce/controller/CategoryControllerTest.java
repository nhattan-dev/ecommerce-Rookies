package com.nhattan.ecommerce.controller;

import com.nhattan.ecommerce.dto.CategoryDTO;
import com.nhattan.ecommerce.entity.CategoryEntity;
import com.nhattan.ecommerce.enums.STATUS;
import com.nhattan.ecommerce.repository.ICategoryRepository;
import com.nhattan.ecommerce.service.impl.CategoryService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@WebMvcTest(CategoryController.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService cateService;

    @MockBean
    private ICategoryRepository cateRepo;

    private List<CategoryEntity> cateList = Arrays.asList(
            new CategoryEntity(1, "cate1", "cate1", "description"
                    , "image", STATUS.NOT_AVAILABLE.name()),
            new CategoryEntity(2, "cate2", "cate2", "description"
                    , "image", STATUS.NOT_AVAILABLE.name()),
            new CategoryEntity(3, "cate3", "cate3", "description"
                    , "image", STATUS.NOT_AVAILABLE.name()),
            new CategoryEntity(4, "cate4", "cate4", "description"
                    , "image", STATUS.NOT_AVAILABLE.name())
    );

    private CategoryDTO dto = new CategoryDTO(0, "", "cate1", "description"
            , "image", "");

    @BeforeEach
    public void init() {

    }

    @Test
    @WithMockUser(username = "admin")
    public void TestShowCategory_with_key_default_then_return_list() throws Exception {
        Mockito.when(cateRepo.findAll()).thenReturn(cateList);
        mockMvc.perform(get("/api/admin/category")
                .contentType(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$.size()", is(cateList.size())));
    }

    @Test
    @WithMockUser(username = "admin")
    public void TestShowCategory_with_key_notavailable_then_return_list() throws Exception {
        Mockito.when(cateRepo.findByStatus(Mockito.anyString())).thenReturn(cateList);
        mockMvc.perform(get("/api/admin/category?key=notavailable")
                .contentType(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$.size()", is(cateList.size())));
    }

    @Test
    @WithMockUser(username = "admin")
    public void TestShowOne_then_return_one() throws Exception {
        Mockito.when((cateRepo.findById(Mockito.anyInt()))).thenReturn(Optional.of(cateList.get(0)));
        mockMvc.perform(get("/api/admin/category/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$.categoryID", is(cateList.get(0).getCategoryID())))
                .andExpect(jsonPath("$.categoryCode", is(cateList.get(0).getCategoryCode())))
                .andExpect(jsonPath("$.categoryName", is(cateList.get(0).getCategoryName())))
                .andExpect(jsonPath("$.imagePath", is(cateList.get(0).getImagePath())))
                .andExpect(jsonPath("$.status", is(cateList.get(0).getStatus())))
                .andExpect(jsonPath("$.description", is(cateList.get(0).getDescription())));
    }

    @Test
    @WithMockUser(username = "admin")
    public void TestShowCategoryAvailable_then_return_list() throws Exception {
        Mockito.when(cateRepo.findByStatus(Mockito.anyString())).thenReturn(cateList);
        mockMvc.perform(get("/api/public/category")
                .contentType(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$.size()", is(cateList.size())));
    }

    @Test
    @WithMockUser(username = "admin")
    public void TestActivityCategory() throws Exception {
        Mockito.when(cateRepo.existsById(1)).thenReturn(true);
//        Mockito.when(cateRepo.activityCategory(Mockito.anyInt()))
        mockMvc.perform(patch("/api/category/{categoryID}/activity", 1)
                .contentType(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin")
    public void TestInvalidateCategory() throws Exception {
//        Mockito.when(cateRepo.existsById(1)).thenReturn(true);
        Mockito.doNothing().when(cateService).invalidateCategory(Mockito.anyInt());
//        Mockito.when(cateRepo.activityCategory(Mockito.anyInt()))
        mockMvc.perform(delete("/api/category/{categoryID}", 1)
                .contentType(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk());
    }
}
