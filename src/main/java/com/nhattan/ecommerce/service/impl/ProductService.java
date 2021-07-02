package com.nhattan.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhattan.ecommerce.entity.ProductColorEntity;
import com.nhattan.ecommerce.entity.ProductEntity;
import com.nhattan.ecommerce.entity.ProductImageEntity;
import com.nhattan.ecommerce.entity.ProductPriceEntity;
import com.nhattan.ecommerce.entity.ProductSizeEntity;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.IProductColorRepository;
import com.nhattan.ecommerce.repository.IProductImageRepository;
import com.nhattan.ecommerce.repository.IProductPriceRepository;
import com.nhattan.ecommerce.repository.IProductRepository;
import com.nhattan.ecommerce.repository.IProductSizeRepository;
import com.nhattan.ecommerce.repository.ISubcategoryRepository;
import com.nhattan.ecommerce.request.CreateProductColorRequest;
import com.nhattan.ecommerce.request.CreateProductImageRequest;
import com.nhattan.ecommerce.request.CreateProductRequest;
import com.nhattan.ecommerce.request.CreateProductSizeRequest;
import com.nhattan.ecommerce.request.UpdateProductRequest;
import com.nhattan.ecommerce.response.ProductResponse;
import com.nhattan.ecommerce.service.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private IProductImageRepository productImageRepository;

	@Autowired
	private IProductPriceRepository productPriceRepository;

	@Autowired
	private IProductSizeRepository productSizeRepository;

	@Autowired
	private IProductColorRepository productColorRepository;

	@Autowired
	private ISubcategoryRepository subcategoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public ProductResponse saveProduct(CreateProductRequest productRequest) {
		if (!subcategoryRepository.exists(productRequest.getSubcategoryID()))
			throw new NotFoundException("subcategory-not-found");

		ProductEntity newProduct = modelMapper.map(productRequest, ProductEntity.class);
		newProduct.setProductID(0);
		int productID = productRepository.save(newProduct).getProductID();

		productRequest.getProductImages().forEach(x -> {
			ProductImageEntity newProductImage = modelMapper.map(x, ProductImageEntity.class);
			newProductImage.setProduct(new ProductEntity(productID));
			productImageRepository.save(newProductImage);
		});

		productRequest.getProductColors().forEach(x -> {
			ProductColorEntity newProductColor = modelMapper.map(x, ProductColorEntity.class);
			newProductColor.setProduct(new ProductEntity(productID));
			productColorRepository.save(newProductColor);
		});

		productRequest.getProductSizes().forEach(x -> {
			ProductSizeEntity newProductSize = modelMapper.map(x, ProductSizeEntity.class);
			newProductSize.setProduct(new ProductEntity(productID));
			productSizeRepository.save(newProductSize);
		});

		ProductPriceEntity newProductPrice = new ProductPriceEntity();
		newProductPrice.setPrice(productRequest.getPrice());
		newProductPrice.setProduct(new ProductEntity(productID));
		productPriceRepository.save(newProductPrice);

		return modelMapper.map(productRepository.findOne(productID), ProductResponse.class);
	}

	@Transactional
	@Override
	public void delete(Integer productID) {
		if (!productRepository.exists(productID))
			throw new NotFoundException("product-not-found");
		productRepository.deleteByProductID(productID);
	}

	// chưa xong
	@Override
	public List<ProductResponse> findAll(Pageable pageable) {
		List<ProductResponse> result = productRepository.findByDeletedEquals(0, pageable).stream()
				.map(x -> modelMapper.map(x, ProductResponse.class)).collect(Collectors.toList());

		// add latest price
		result = result.stream().map(x -> {
			x.setPrice(productPriceRepository.getLatestPriceByProductID(x.getProductID()));
			return x;
		}).collect(Collectors.toList());

		return result;
	}

	@Override
	public int totalItem() {
		return (int) productRepository.count();
	}

	@Transactional
	@Override
	public ProductResponse update(UpdateProductRequest productRequest) {
		// category & sub-category != 1
		if (!productRepository.exists(productRequest.getProductID()))
			throw new NotFoundException("product-not-found");
		if (!subcategoryRepository.exists(productRequest.getSubcategoryID()))
			throw new NotFoundException("subcategory-not-found");
		productRepository.updateByProductID(productRequest.getName(), productRequest.getDescription(),
				productRequest.getSubcategoryID(), productRequest.getQuantity(), productRequest.getProductID());
		if (productPriceRepository.getLatestPriceByProductID(productRequest.getProductID()) != productRequest
				.getPrice()) {
			ProductPriceEntity newPrice = new ProductPriceEntity();
			newPrice.setPrice(productRequest.getPrice());
			ProductEntity product = new ProductEntity();
			product.setProductID(productRequest.getProductID());
			newPrice.setProduct(product);
			productPriceRepository.save(newPrice);
		}
//		return modelMapper.map(productRepository.findOne(productRequest.getProductID()), ProductResponse.class);
		return null;
	}

	@Override
	public ProductResponse findOne(int productID) {
		if (!productRepository.exists(productID))
			throw new NotFoundException("product-not-found");
		ProductResponse product = modelMapper.map(productRepository.findOne(productID), ProductResponse.class);
		product.setPrice(productPriceRepository.getLatestPriceByProductID(productID));
		return product;
	}

	@Override
	public ProductResponse saveColor(CreateProductColorRequest colorRequest) {
		ProductEntity product = productRepository.findOne(colorRequest.getProductID());
		if (product == null)
			throw new NotFoundException("product-not-found");

		if (productColorRepository.existsProductColorByColorLike(colorRequest.getColor()))
			throw new ConflictException("product-alread-has-color");

		ProductColorEntity newColor = modelMapper.map(colorRequest, ProductColorEntity.class);
		int defaultProductColorID = 0;
		newColor.setProductColorID(defaultProductColorID);
		productColorRepository.save(newColor);

		return modelMapper.map(productRepository.findOne(colorRequest.getProductID()), ProductResponse.class);
	}

	@Override
	public ProductResponse saveImage(CreateProductImageRequest imageRequest) {
		ProductEntity product = productRepository.findOne(imageRequest.getProductID());
		if (product == null)
			throw new NotFoundException("product-not-found");

		if (productImageRepository.findProductImageByImagePath(imageRequest.getImagePath()) != null)
			throw new ConflictException("product-alread-has-image");

		ProductImageEntity newImage = modelMapper.map(imageRequest, ProductImageEntity.class);
		int defaultProductImageID = 0;
		newImage.setProductImageID(defaultProductImageID);
		productImageRepository.save(newImage);

		return modelMapper.map(productRepository.findOne(imageRequest.getProductID()), ProductResponse.class);
	}

	@Override
	public ProductResponse saveSize(CreateProductSizeRequest sizeRequest) {
		ProductEntity product = productRepository.findOne(sizeRequest.getProductID());
		if (product == null)
			throw new NotFoundException("product-not-found");

		if (productSizeRepository.findProductSizeBySize(sizeRequest.getSize()).isPresent())
			throw new ConflictException("product-alread-has-size");

		ProductSizeEntity newSize = modelMapper.map(sizeRequest, ProductSizeEntity.class);
		int defaultProductSize = 0;
		newSize.setProductSizeID(defaultProductSize);
		productSizeRepository.save(newSize);

		return modelMapper.map(productRepository.findOne(sizeRequest.getProductID()), ProductResponse.class);
	}

	@Override
	public String deleteColor(int productColorID) {
		if (!productColorRepository.exists(productColorID))
			throw new NotFoundException("color-not-found");
		productColorRepository.delete(productColorID);
		return "success";
	}

	@Override
	public String deleteImage(int productImageID) {
		if (!productImageRepository.exists(productImageID))
			throw new NotFoundException("image-not-found");
		productImageRepository.delete(productImageID);
		return "success";
	}

	@Override
	public String deleteSize(int productSizeID) {
		if (!productSizeRepository.exists(productSizeID))
			throw new NotFoundException("size-not-found");
		productSizeRepository.delete(productSizeID);
		return "success";
	}

}
