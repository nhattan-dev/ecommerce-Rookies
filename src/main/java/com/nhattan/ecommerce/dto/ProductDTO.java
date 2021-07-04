package com.nhattan.ecommerce.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class ProductDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productID;
	@NotBlank(message = "cannot-be-empty")
	private String name;
	private String description;
	private int point;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int quantity;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private long price;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int subcategoryID;
	private Set<ProductImageDTO> productImages = new HashSet<ProductImageDTO>();
	private Set<ProductColorDTO> productColors = new HashSet<ProductColorDTO>();
	private Set<ProductSizeDTO> productSizes = new HashSet<ProductSizeDTO>();

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getSubcategoryID() {
		return subcategoryID;
	}

	public void setSubcategoryID(int subcategoryID) {
		this.subcategoryID = subcategoryID;
	}

	public Set<ProductImageDTO> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ProductImageDTO> productImages) {
		this.productImages = productImages;
	}

	public Set<ProductColorDTO> getProductColors() {
		return productColors;
	}

	public void setProductColors(Set<ProductColorDTO> productColors) {
		this.productColors = productColors;
	}

	public Set<ProductSizeDTO> getProductSizes() {
		return productSizes;
	}

	public void setProductSizes(Set<ProductSizeDTO> productSizes) {
		this.productSizes = productSizes;
	}

}
