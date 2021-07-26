package com.nhattan.ecommerce.service.impl;

import com.nhattan.ecommerce.dto.CategoryDTO;
import com.nhattan.ecommerce.dto.SubcategoryDTO;
import com.nhattan.ecommerce.entity.CategoryEntity;
import com.nhattan.ecommerce.enums.STATUS;
import com.nhattan.ecommerce.exception.BadRequestException;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.ICategoryRepository;
import com.nhattan.ecommerce.service.ICategoryService;
import com.nhattan.ecommerce.util.DriveAPIUtils;
import com.nhattan.ecommerce.util.VNCharacterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Value("${upload.path}")
    private String fileUpload;

    @Override
    public CategoryDTO saveCategory(CategoryDTO dto) {
        CategoryEntity newCategory = CategoryDTO.toEntity(dto);
        newCategory.setImagePath(uploadImage(dto.getImagePath()));
        String categoryCode = VNCharacterUtils.removeAccent(dto.getCategoryName().toLowerCase())
                .replace(" ", "-");
        checkCategoryCodeUsed(categoryCode);
        newCategory.setCategoryCode(categoryCode);
        return CategoryDTO.toDTO(categoryRepository.save(newCategory));
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(x -> {
            CategoryDTO dto = CategoryDTO.toDTO(x);
            dto.setSubcategories(x.getSubcategories().stream().map(SubcategoryDTO::toDTO)
                    .collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findOne(int categoryID) {
        return CategoryDTO.toDTO(categoryRepository.findById(categoryID)
                .orElseThrow(() -> new NotFoundException("category-not-found")));
    }

    @Override
    public List<CategoryDTO> findCategoryAvailable() {
        List<CategoryEntity> list = categoryRepository.findByStatus(STATUS.AVAILABLE.name());
        list = list.stream().map(x -> {
            System.out.println(x.getSubcategories().size());
            x.setSubcategories(x.getSubcategories().stream().filter(e -> e.getStatus().equals(STATUS.AVAILABLE.name()))
                    .collect(Collectors.toSet()));
            return x;
        }).collect(Collectors.toList());
        return list.stream().map(x -> {
            CategoryDTO dto = CategoryDTO.toDTO(x);
            dto.setSubcategories(x.getSubcategories().stream().map(SubcategoryDTO::toDTO)
                    .collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> findCategoryNotAvailable() {
        List<CategoryEntity> list = categoryRepository.findByStatus(STATUS.NOT_AVAILABLE.name());
        list = list.stream().map(x -> {
            x.setSubcategories(x.getSubcategories().stream().filter(e -> e.getStatus().equals(STATUS.NOT_AVAILABLE.name()))
                    .collect(Collectors.toSet()));
            return x;
        }).collect(Collectors.toList());
        return list.stream().map(x -> {
            CategoryDTO dto = CategoryDTO.toDTO(x);
            dto.setSubcategories(x.getSubcategories().stream().map(SubcategoryDTO::toDTO)
                    .collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void invalidateCategory(Integer id) {
        if (!categoryRepository.existsById(id))
            throw new NotFoundException("category-not-found");
        categoryRepository.deleteByCategoryID(id);
    }

    @Transactional
    @Override
    public CategoryDTO updateCategory(CategoryDTO dto) {
        if (!categoryRepository.existsById(dto.getCategoryID())) {
            throw new NotFoundException("category-not-found");
        }

        String categoryCode = VNCharacterUtils.removeAccent(dto.getCategoryName().toLowerCase())
                .replace(" ", "-");
        if (categoryRepository.existsCategoryByCategoryCodeAndCategoryIDNot(categoryCode,
                dto.getCategoryID())) {
            throw new ConflictException("try-another-name");
        }
        dto.setImagePath(DriveAPIUtils.isDriveImageUrl(dto.getImagePath()) ? dto.getImagePath() : uploadImage(dto.getImagePath()));
        CategoryEntity entity = CategoryDTO.toEntity(dto);
        entity.setCategoryCode(categoryCode);
        return CategoryDTO.toDTO(categoryRepository.save(entity));
    }

    @Transactional
    @Override
    public String activityCategory(int categoryID) {
        if (!categoryRepository.existsById(categoryID))
            throw new NotFoundException("category_not-found");
        categoryRepository.activityCategory(categoryID);
        return "successfully";
    }

    private void checkCategoryCodeUsed(String categoryCode) {
        if (categoryRepository.existsCategoryByCategoryCode(categoryCode)) {
            throw new ConflictException("categorycode-already-exists");
        }
    }

//    private List<String> verifyImagePaths(List<String> imagePaths) {
//        return imagePaths.stream().map(i -> DriveAPIUtils.isDriveImageUrl(i) ? i : uploadImage(i))
//                .collect(Collectors.toList());
//    }

    private String uploadImage(String imagePath) {
        String fileName = UUID.randomUUID().toString();
        try {
            FileCopyUtils.copy(Base64Utils.decodeFromString(imagePath.split(",")[1]),
                    new File(this.fileUpload + fileName));
            return DriveAPIUtils.upload(new File(fileUpload + fileName));
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            throw new BadRequestException("image-wrong");
        }
    }
}
