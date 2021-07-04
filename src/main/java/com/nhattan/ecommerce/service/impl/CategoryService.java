package com.nhattan.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhattan.ecommerce.dto.CategoryDTO;
import com.nhattan.ecommerce.entity.CategoryEntity;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.ICategoryRepository;
import com.nhattan.ecommerce.service.ICategoryService;
import com.nhattan.ecommerce.util.VNCharacterUtils;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO save(CategoryDTO category) {
		CategoryEntity newCategory = modelMapper.map(category, CategoryEntity.class);
		String categoryCode = VNCharacterUtils.removeAccent(category.getCategoryName().toLowerCase()).replace(" ", "-");
		if (categoryRepository.existsCategoryByCategoryCode(categoryCode)) {
			throw new ConflictException("categorycode-already-exists");
		}
		newCategory.setCategoryCode(categoryCode);
		return modelMapper.map(categoryRepository.save(newCategory), CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> findAll() {
		return categoryRepository.findAll().stream().map(x -> modelMapper.map(x, CategoryDTO.class))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		if (!categoryRepository.existsCategoryByCategoryID(id)) {
			throw new NotFoundException("category-not-found");
		}
		categoryRepository.deleteByCategoryID(id);
	}

	@Transactional
	@Override
	public CategoryDTO update(CategoryDTO categoryRequest) {
		if (!categoryRepository.exists(categoryRequest.getCategoryID())) {
			throw new NotFoundException("category-not-found");
		}

		String categoryCode = VNCharacterUtils.removeAccent(categoryRequest.getCategoryName().toLowerCase())
				.replace(" ", "-");
		if (categoryRepository.existsCategoryByCategoryCodeAndCategoryIDNot(categoryCode,
				categoryRequest.getCategoryID())) {
			throw new ConflictException("try-another-name");
		}

		categoryRepository.updateByCategoryID(categoryRequest.getCategoryName(), categoryCode,
				categoryRequest.getDescription(), categoryRequest.getImagePath(), categoryRequest.getCategoryID());

		return modelMapper.map(categoryRepository.findOne(categoryRequest.getCategoryID()), CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> findAllValid() {
		int NotDeleteValue = 0;
		List<CategoryEntity> list = categoryRepository.findByDeleted(NotDeleteValue);
		list = list.stream().map(x -> {
			x.setSubcategories(x.getSubcategories().stream().filter(e -> e.getDeleted() == NotDeleteValue)
					.collect(Collectors.toList()));
			return x;
		}).collect(Collectors.toList());
		return list.stream().map(x -> modelMapper.map(x, CategoryDTO.class)).collect(Collectors.toList());
	}
}