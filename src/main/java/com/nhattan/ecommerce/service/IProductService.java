package com.nhattan.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.nhattan.ecommerce.request.CreateProductColorRequest;
import com.nhattan.ecommerce.request.CreateProductImageRequest;
import com.nhattan.ecommerce.request.CreateProductRequest;
import com.nhattan.ecommerce.request.CreateProductSizeRequest;
import com.nhattan.ecommerce.request.UpdateProductRequest;
import com.nhattan.ecommerce.response.ProductResponse;

public interface IProductService {
	ProductResponse saveProduct(CreateProductRequest productRequest);

	ProductResponse update(UpdateProductRequest productRequest);

	void delete(Integer id);

	List<ProductResponse> findAll(Pageable pageable);

	ProductResponse findOne(int productID);

	int totalItem();

	ProductResponse saveColor(CreateProductColorRequest colorRequest);

	ProductResponse saveImage(CreateProductImageRequest imageRequest);

	ProductResponse saveSize(CreateProductSizeRequest sizeRequest);

	String deleteColor(int productColorID);

	String deleteImage(int productImageID);

	String deleteSize(int productSizeID);
}
