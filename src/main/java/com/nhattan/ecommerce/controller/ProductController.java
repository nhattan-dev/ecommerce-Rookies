package com.nhattan.ecommerce.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhattan.ecommerce.controller.output.ProductOutput;
import com.nhattan.ecommerce.dto.ProductColorDTO;
import com.nhattan.ecommerce.dto.ProductDTO;
import com.nhattan.ecommerce.dto.ProductImageDTO;
import com.nhattan.ecommerce.dto.ProductSizeDTO;
import com.nhattan.ecommerce.service.IProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	private IProductService productService;

	@GetMapping(value = "/public/product")
	public ProductOutput showProductAvailable(
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		ProductOutput result = new ProductOutput();
		result.setPage(page);
		result.setListResult(productService.findProductAvailable(page, limit, sort));
		result.setTotalPage((int) Math.ceil((double) (productService.totalAvailableItem()) / limit));
		return result;
	}

	@GetMapping(value = "/public/{subcategoryID}/product")
	public ProductOutput showProductAvailableBySubcategory(
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
			@PathVariable("subcategoryID") int subcategoryID) {
		ProductOutput result = new ProductOutput();
		result.setPage(page);
		result.setListResult(productService.findProductAvailableBySubcategoryID(page, limit, sort, subcategoryID));
		result.setTotalPage(
				(int) Math.ceil((double) (productService.totalAvailableItemBySubcategory(subcategoryID)) / limit));
		return result;
	}

	@GetMapping(value = "/admin/product")
	public ProductOutput showProduct(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
			@RequestParam(name = "key", required = false, defaultValue = "all") String key) {
		ProductOutput result = new ProductOutput();
		result.setPage(page);
		Pageable pageable = new PageRequest(page - 1, limit);
		String showNotAvailableValue = "notavailable";
		if (key.equalsIgnoreCase(showNotAvailableValue)) {
			result.setListResult(productService.findProductNotAvailable(page, limit, sort));
			result.setTotalPage((int) Math.ceil((double) (productService.totalNotAvailableItem()) / limit));
		} else {
			result.setListResult(productService.findAll(pageable));
			result.setTotalPage((int) Math.ceil((double) (productService.totalItem()) / limit));
		}
		return result;
	}

	@GetMapping(value = "/admin/product/{productID}")
	public ProductDTO showOneProduct(@PathVariable int productID) {
		return productService.findOneProduct(productID);
	}

	@GetMapping(value = "/public/product/{productID}")
	public ProductDTO showOneProductAvailable(@PathVariable int productID) {
		return productService.findOneProductAvailable(productID);
	}

	@PostMapping(value = "/admin/product")
	public ProductDTO createProduct(@Valid @RequestBody ProductDTO productRequest) {
		return productService.saveProduct(productRequest);
	}

	@PostMapping(value = "/admin/product/image")
	public ProductImageDTO createProductImage(@Valid @RequestBody ProductImageDTO productImageRequest) {
		return productService.saveImage(productImageRequest);
	}

	@DeleteMapping(value = "/admin/product/image/{productImageID}")
	public String invalidateProductImage(@PathVariable("productImageID") int productImageID) {
		return productService.deleteImage(productImageID);
	}

	@PostMapping(value = "/admin/product/size")
	public ProductSizeDTO createProductSize(@Valid @RequestBody ProductSizeDTO productSizeRequest) {
		return productService.saveSize(productSizeRequest);
	}

	@DeleteMapping(value = "/admin/product/size/{productSizeID}")
	public String invalidateProductSize(@PathVariable("productSizeID") int productSizeID) {
		return productService.deleteSize(productSizeID);
	}

	@PostMapping(value = "/admin/product/color")
	public ProductColorDTO createProductColor(@Valid @RequestBody ProductColorDTO productColorRequest) {
		return productService.saveColor(productColorRequest);
	}

	@DeleteMapping(value = "/admin/product/color/{productColorID}")
	public String invalidateProductColor(@PathVariable("productColorID") int productColorID) {
		return productService.deleteColor(productColorID);
	}

	@PutMapping(value = "/admin/product/{id}")
	public ProductDTO updateProduct(@Valid @RequestBody ProductDTO productRequest, @PathVariable int id) {
		productRequest.setProductID(id);
		return productService.updateProduct(productRequest);
	}

	@PatchMapping(value = "admin/product/{productID}/reactivity")
	public String reactivityProduct(@PathVariable("productID") int productID) {
		return productService.reactivityProduct(productID);
	}

	@DeleteMapping(value = "/admin/product/{id}")
	public void invalidateProduct(@PathVariable int id) {
		productService.invalidateProduct(new Integer(id));
	}
}
