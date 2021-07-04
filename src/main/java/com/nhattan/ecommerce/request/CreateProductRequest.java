package com.nhattan.ecommerce.request;

import java.util.Set;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class CreateProductRequest {
	@NotBlank(message = "cannot-be-empty")
	private String name;
	private String description;
	@Min(value = 0, message = "must-be-greater-than-or-equals-0")
	private int subcategoryID;
	@Min(value = 0, message = "must-be-greater-than-or-equals-0")
	private int quantity;
	private Set<ProductImageRequest> productImages;
	private Set<ProductColorRequest> productColors;
	private Set<ProductSizeRequest> productSizes;
	@Min(value = 0, message = "must-be-greater-than-or-equals-0")
	private long price;

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

	public int getSubcategoryID() {
		return subcategoryID;
	}

	public void setSubcategoryID(int subcategoryID) {
		this.subcategoryID = subcategoryID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Set<ProductImageRequest> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ProductImageRequest> productImages) {
		this.productImages = productImages;
	}

	public Set<ProductColorRequest> getProductColors() {
		return productColors;
	}

	public void setProductColors(Set<ProductColorRequest> productColors) {
		this.productColors = productColors;
	}

	public Set<ProductSizeRequest> getProductSizes() {
		return productSizes;
	}

	public void setProductSizes(Set<ProductSizeRequest> productSizes) {
		this.productSizes = productSizes;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

}
