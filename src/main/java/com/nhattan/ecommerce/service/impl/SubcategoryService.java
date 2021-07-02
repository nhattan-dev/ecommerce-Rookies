package com.nhattan.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhattan.ecommerce.entity.SubcategoryEntity;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.ICategoryRepository;
import com.nhattan.ecommerce.repository.ISubcategoryRepository;
import com.nhattan.ecommerce.request.CreateSubcategoryRequest;
import com.nhattan.ecommerce.request.UpdateSubcategoryRequest;
import com.nhattan.ecommerce.response.ReadSubcagoryResponse;
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
	public ReadSubcagoryResponse save(CreateSubcategoryRequest subcategoryRequest) {
		if (!categoryRepository.exists(subcategoryRequest.getCategoryID())) {
			throw new NotFoundException("category-not-found");
		}
		SubcategoryEntity newSubcategory = modelMapper.map(subcategoryRequest, SubcategoryEntity.class);
		newSubcategory.setSubcategoryID(0);
		String subcategoryCode = VNCharacterUtils.removeAccent(subcategoryRequest.getSubcategoryName().toLowerCase())
				.replace(" ", "-");
		if (subcategoryRepository.existsSubcategoryBySubcategoryCode(subcategoryCode)) {
			throw new ConflictException("try-another-name");
		}
		newSubcategory.setSubcategoryCode(subcategoryCode);

		return modelMapper.map(subcategoryRepository.save(newSubcategory), ReadSubcagoryResponse.class);
	}

	@Transactional
	@Override
	public void delete(Integer subcategoryID) {
		if (!subcategoryRepository.exists(subcategoryID)) {
			throw new NotFoundException("subcategory-not-found");
		}
		subcategoryRepository.deleteBySubcategoryID(subcategoryID);
	}

	@Override
	public List<ReadSubcagoryResponse> findAll() {
		subcategoryRepository.findAll().forEach(x -> {
			x.getProducts().forEach(y -> {
				System.out.println(y.getPoint());
			});
		});
		return null;
//		return subcategoryRepository.findAll().stream().map(x -> modelMapper.map(x, ReadSubcagoryResponse.class))
//				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public ReadSubcagoryResponse update(UpdateSubcategoryRequest subcategoryReqest) {
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
				ReadSubcagoryResponse.class);
	}

	@Override
	public ReadSubcagoryResponse findOne(int subcategoryID) {
		if (!subcategoryRepository.exists(subcategoryID)) {
			throw new NotFoundException("subcategory-not-found");
		}
		return modelMapper.map(subcategoryRepository.findOne(subcategoryID), ReadSubcagoryResponse.class);
	}

	@Override
	public List<ReadSubcagoryResponse> findSubcategoryValid() {
		int NotDeletedValue = 0;
		List<SubcategoryEntity> list = subcategoryRepository.findByDeleted(NotDeletedValue);

//		// remove all products with deleted != 0
//		list = list.stream().map(x -> {
//			x.setProducts(x.getProducts().stream().filter(e -> e.getDeleted() == NotDeletedValue)
//					.collect(Collectors.toList()));
//			return x;
//		}).collect(Collectors.toList());

		return list.stream().map(x -> modelMapper.map(x, ReadSubcagoryResponse.class)).collect(Collectors.toList());
	}

}
