package com.nhattan.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.nhattan.ecommerce.dto.ProductColorDTO;
import com.nhattan.ecommerce.dto.ProductDTO;
import com.nhattan.ecommerce.dto.ProductImageDTO;
import com.nhattan.ecommerce.dto.ProductSizeDTO;

public interface IProductService {
	ProductDTO saveProduct(ProductDTO productRequest);

	ProductDTO updateProduct(ProductDTO productRequest);

	void invalidateProduct(Integer id);

	List<ProductDTO> findAll(Pageable pageable);

	List<ProductDTO> findProductNotAvailable(int page, int limit, String sort);

	List<ProductDTO> findProductAvailable(int page, int limit, String sort);

	List<ProductDTO> findProductAvailableBySubcategoryID(int page, int limit, String sort, int subcategoryID);

	ProductDTO findOneProduct(int productID);

	ProductDTO findOneProductAvailable(int productID);

	int totalItem();

	int totalAvailableItem();

	int totalNotAvailableItem();

	int totalAvailableItemBySubcategory(int subcategoryID);

	ProductColorDTO saveColor(ProductColorDTO colorRequest);

	ProductImageDTO saveImage(ProductImageDTO imageRequest);

	ProductSizeDTO saveSize(ProductSizeDTO sizeRequest);

	String deleteColor(int productColorID);

	String deleteImage(int productImageID);

	String deleteSize(int productSizeID);
	
	String reactivityProduct(int productID);
}
