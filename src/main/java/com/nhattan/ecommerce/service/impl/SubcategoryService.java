package com.nhattan.ecommerce.service.impl;

import com.nhattan.ecommerce.dto.SubcategoryDTO;
import com.nhattan.ecommerce.entity.SubcategoryEntity;
import com.nhattan.ecommerce.enums.STATUS;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.ICategoryRepository;
import com.nhattan.ecommerce.repository.ISubcategoryRepository;
import com.nhattan.ecommerce.service.ISubcategoryService;
import com.nhattan.ecommerce.util.VNCharacterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubcategoryService implements ISubcategoryService {

    @Autowired
    private ISubcategoryRepository subcategoryRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Transactional
    @Override
    public SubcategoryDTO saveSubcategory(SubcategoryDTO dto) {
        if (!categoryRepository.existsById(dto.getCategoryID())) {
            throw new NotFoundException("category-not-found");
        }
        SubcategoryEntity newSubcategory = SubcategoryDTO.toEntity(dto);
        newSubcategory.setSubcategoryID(0);
        String subcategoryCode = VNCharacterUtils.removeAccent(dto.getSubcategoryName().toLowerCase())
                .replace(" ", "-");
        if (subcategoryRepository.existsSubcategoryBySubcategoryCodeAndSubcategoryIDNot(subcategoryCode,
                dto.getSubcategoryID())) {
            throw new ConflictException("try-another-name");
        }
        newSubcategory.setSubcategoryCode(subcategoryCode);

        return SubcategoryDTO.toDTO(subcategoryRepository.save(newSubcategory));
    }

    @Transactional
    @Override
    public String invalidateSubcategory(Integer subcategoryID) {
        if (!subcategoryRepository.existsById(subcategoryID)) {
            throw new NotFoundException("subcategory-not-found");
        }
        subcategoryRepository.deleteBySubcategoryID(subcategoryID);
        return "successfully";
    }

    @Override
    public List<SubcategoryDTO> findAll() {
        return subcategoryRepository.findAll().stream().map(x -> SubcategoryDTO.toDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public SubcategoryDTO updateSubcategory(SubcategoryDTO dto) {
        if (!subcategoryRepository.existsById(dto.getSubcategoryID())) {
            throw new NotFoundException("subcategory-not-found");
        }
        if (!categoryRepository.existsById(dto.getCategoryID())) {
            throw new NotFoundException("category-not-found");
        }
        String subcategoryCode = VNCharacterUtils.removeAccent(dto.getSubcategoryName().toLowerCase())
                .replace(" ", "-");
        if (subcategoryRepository.existsBySubcategoryCodeAndSubcategoryIDNot(subcategoryCode, dto.getSubcategoryID()))
            throw new ConflictException("subcategorycode-already-use.-try-another-name");

        subcategoryRepository.updateBySubcategoryID(dto.getSubcategoryName(), subcategoryCode,
                dto.getDescription(), dto.getCategoryID(),
                dto.getSubcategoryID());
        return SubcategoryDTO.toDTO(subcategoryRepository.findById(dto.getSubcategoryID()).get());
    }

    @Override
    public SubcategoryDTO findOneSubcategory(int subcategoryID) {
        return SubcategoryDTO.toDTO(subcategoryRepository.findById(subcategoryID)
                .orElseThrow(() -> new NotFoundException("subcategory-not-found")));
    }

    @Override
    public SubcategoryDTO findOneSubcategoryAvailable(int subcategoryID) {
        return SubcategoryDTO.toDTO(subcategoryRepository.findOneByAvailable(subcategoryID)
                .orElseThrow(() -> new NotFoundException("subcategory-not-found")));
    }

    @Override
    public List<SubcategoryDTO> findSubcategoryAvailable() {
        List<SubcategoryEntity> list = subcategoryRepository.findByStatusAndCategory_Status(STATUS.AVAILABLE.name(),
                STATUS.AVAILABLE.name());
        return list.stream().map(x -> SubcategoryDTO.toDTO(x)).collect(Collectors.toList());
    }

    @Override
    public List<SubcategoryDTO> findSubcategoryNotAvailable() {
        List<SubcategoryEntity> list = subcategoryRepository.findByStatusOrCategory_Status(STATUS.NOT_AVAILABLE.name(),
                STATUS.NOT_AVAILABLE.name());
        return list.stream().map(s -> SubcategoryDTO.toDTO(s)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public String activitySubcategory(int subcategoryID) {
        if(subcategoryRepository.findById(subcategoryID).isEmpty())
            throw new NotFoundException("subcategory-not-found");
        subcategoryRepository.activitySubcategory(subcategoryID);
        return "successfully";
    }

}
