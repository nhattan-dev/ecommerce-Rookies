package com.nhattan.ecommerce.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhattan.ecommerce.controller.output.ProductOutput;
import com.nhattan.ecommerce.request.CreateProductColorRequest;
import com.nhattan.ecommerce.request.CreateProductImageRequest;
import com.nhattan.ecommerce.request.CreateProductRequest;
import com.nhattan.ecommerce.request.CreateProductSizeRequest;
import com.nhattan.ecommerce.request.UpdateProductRequest;
import com.nhattan.ecommerce.response.ProductResponse;
import com.nhattan.ecommerce.service.IProductService;

@RestController
public class ProductController {
	@Autowired
	private IProductService productService;

	@GetMapping(value = "/product")
	public ProductOutput showProduct(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		ProductOutput result = new ProductOutput();
		result.setPage(page);
//		Sort sortable = null;
		Pageable pageable = new PageRequest(page - 1, limit);
		result.setListResult(productService.findAll(pageable));
		result.setTotalPage((int) Math.ceil((double) (productService.totalItem()) / limit));
		return result;
	}

	@GetMapping(value = "/product/{productID}")
	public ProductResponse showOneProduct(@PathVariable int productID) {
		return productService.findOne(productID);
	}

	@PostMapping(value = "/product")
	public ProductResponse createProduct(@Valid @RequestBody CreateProductRequest productRequest) {
		return productService.saveProduct(productRequest);
	}

	@PostMapping(value = "/product/image")
	public ProductResponse createProductImage(@Valid @RequestBody CreateProductImageRequest productImageRequest) {
		return productService.saveImage(productImageRequest);
	}

	@DeleteMapping(value = "/product/image/{productImageID}")
	public String deleteProductImage(@PathVariable("productImageID") int productImageID) {
		return productService.deleteImage(productImageID);
	}

	@PostMapping(value = "/product/size")
	public ProductResponse createProductSize(@Valid @RequestBody CreateProductSizeRequest productSizeRequest) {
		return productService.saveSize(productSizeRequest);
	}

	@DeleteMapping(value = "/product/size/{productSizeID}")
	public String deleteProductSize(@PathVariable("productSizeID") int productSizeID) {
		return productService.deleteSize(productSizeID);
	}

	@PostMapping(value = "/product/color")
	public ProductResponse createProductColor(@Valid @RequestBody CreateProductColorRequest productColorRequest) {
		return productService.saveColor(productColorRequest);
	}

	@DeleteMapping(value = "/product/color/{productColorID}")
	public String deleteProductColor(@PathVariable("productColorID") int productColorID) {
		return productService.deleteColor(productColorID);
	}

	@PutMapping(value = "/product/{id}")
	public ProductResponse updateProduct(@Valid @RequestBody UpdateProductRequest productRequest,
			@PathVariable int id) {
		productRequest.setProductID(id);
		return productService.update(productRequest);
	}

	@DeleteMapping(value = "/product/{id}")
	public void deleteProduct(@PathVariable int id) {
		productService.delete(new Integer(id));
	}
}
