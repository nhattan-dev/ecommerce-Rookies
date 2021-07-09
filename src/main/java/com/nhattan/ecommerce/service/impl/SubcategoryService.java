package com.nhattan.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhattan.ecommerce.dto.SubcategoryDTO;
import com.nhattan.ecommerce.entity.SubcategoryEntity;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.ICategoryRepository;
import com.nhattan.ecommerce.repository.ISubcategoryRepository;
import com.nhattan.ecommerce.service.ISubcategoryService;
import com.nhattan.ecommerce.util.VNCharacterUtils;

@Service
public class SubcategoryService implements ISubcategoryService {

	@Autowired
	private ISubcategoryRepository subcategoryRepository;

	@Autowired
	private ICategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public SubcategoryDTO saveSubcategory(SubcategoryDTO subcategoryDTO) {
		if (!categoryRepository.exists(subcategoryDTO.getCategoryID())) {
			throw new NotFoundException("category-not-found");
		}
		SubcategoryEntity newSubcategory = SubcategoryDTO.toEntity(subcategoryDTO);
		newSubcategory.setSubcategoryID(0);
		String subcategoryCode = VNCharacterUtils.removeAccent(subcategoryDTO.getSubcategoryName().toLowerCase())
				.replace(" ", "-");
		if (subcategoryRepository.existsSubcategoryBySubcategoryCode(subcategoryCode)) {
			throw new ConflictException("try-another-name");
		}
		newSubcategory.setSubcategoryCode(subcategoryCode);

		return modelMapper.map(subcategoryRepository.save(newSubcategory), SubcategoryDTO.class);
	}

	@Transactional
	@Override
	public void invalidateSubcategory(Integer subcategoryID) {
		if (!subcategoryRepository.exists(subcategoryID)) {
			throw new NotFoundException("subcategory-not-found");
		}
		subcategoryRepository.deleteBySubcategoryID(subcategoryID);
	}

	@Override
	public List<SubcategoryDTO> findAll() {
		return subcategoryRepository.findAll().stream().map(x -> SubcategoryDTO.toDTO(x)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public SubcategoryDTO updateSubcategory(SubcategoryDTO subcategoryReqest) {
		if (!subcategoryRepository.exists(subcategoryReqest.getSubcategoryID())) {
			throw new NotFoundException("subcategory-not-found");
		}
		if (!categoryRepository.exists(subcategoryReqest.getCategoryID())) {
			throw new NotFoundException("category-not-found");
		}
		String subcategoryCode = VNCharacterUtils.removeAccent(subcategoryReqest.getSubcategoryName().toLowerCase())
				.replace(" ", "-");
		if (!subcategoryRepository.existsSubcategoryBySubcategoryCode(subcategoryCode)) {
			throw new ConflictException("subcategorycode-already-use.-try-another-name");
		}

		subcategoryRepository.updateBySubcategoryID(subcategoryReqest.getSubcategoryName(), subcategoryCode,
				subcategoryReqest.getDescription(), subcategoryReqest.getCategoryID(),
				subcategoryReqest.getSubcategoryID());
		return modelMapper.map(subcategoryRepository.findOne(subcategoryReqest.getSubcategoryID()),
				SubcategoryDTO.class);
	}

	@Override
	public SubcategoryDTO findOneSubcategory(int subcategoryID) {
		SubcategoryEntity subcategory = subcategoryRepository.findOne(subcategoryID);
		if (subcategory == null)
			throw new NotFoundException("subcategory-not-found");
		return SubcategoryDTO.toDTO(subcategory);
	}

	@Override
	public SubcategoryDTO findOneSubcategoryAvailable(int subcategoryID) {
		int NotDeletedValue = 0;
		SubcategoryEntity subcategory = subcategoryRepository.findOneByValid(subcategoryID, NotDeletedValue);
		if (subcategory == null)
			throw new NotFoundException("subcategory-not-found");
		// remove all deleted products
		subcategory.setProducts(subcategory.getProducts().stream().filter(p -> p.getDeleted() == NotDeletedValue)
				.collect(Collectors.toList()));
		return SubcategoryDTO.toDTO(subcategory);
	}

	@Override
	public List<SubcategoryDTO> findSubcategoryAvailable() {
		int NotDeletedValue = 0;
		List<SubcategoryEntity> list = subcategoryRepository.findByDeleted(NotDeletedValue);
		// remove all deleted products
		list = list.stream().map(x -> {
			x.setProducts(x.getProducts().stream().filter(p -> p.getDeleted() == NotDeletedValue)
					.collect(Collectors.toList()));
			return x;
		}).collect(Collectors.toList());
		return list.stream().map(x -> SubcategoryDTO.toDTO(x)).collect(Collectors.toList());
	}

	@Override
	public List<SubcategoryDTO> findSubcategoryNotAvailable() {
		List<SubcategoryEntity> list = subcategoryRepository.findByNotAvailable();
		return list.stream().map(s -> SubcategoryDTO.toDTO(s)).collect(Collectors.toList());
	}

	@Override
	public String reactivitySubcategory(int subcategoryID) {
		int notDeleteValue = 0;
		subcategoryRepository.reactivitySubcategory(subcategoryID, notDeleteValue);
		return "successfully";
	}

}
