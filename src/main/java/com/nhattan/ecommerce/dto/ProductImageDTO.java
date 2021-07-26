package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.ProductEntity;
import com.nhattan.ecommerce.entity.ProductImageEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

public class ProductImageDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productImageID;
	@NotBlank(message = "cannot-be-empty")
	private String imagePath;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productID;

	public ProductImageDTO() {
		super();
	}

	public ProductImageDTO(int productImageID, String imagePath) {
		super();
		this.productImageID = productImageID;
		this.imagePath = imagePath;
	}

	public int getProductImageID() {
		return productImageID;
	}

	public void setProductImageID(int productImageID) {
		this.productImageID = productImageID;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public static ProductImageDTO toDTO(ProductImageEntity entity){
		ProductImageDTO dto = new ProductImageDTO();
		dto.setProductID(entity.getProduct().getProductID());
		dto.setImagePath(entity.getImagePath());
		dto.setProductImageID(entity.getProductImageID());
		return dto;
	}

	public static ProductImageEntity toEntity(ProductImageDTO dto){
		ProductImageEntity entity = new ProductImageEntity();
		entity.setImagePath(dto.getImagePath());
		entity.setProductImageID(dto.productImageID);
		ProductEntity product = new ProductEntity();
		product.setProductID(dto.getProductID());
		entity.setProduct(product);
		return entity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductImageDTO other = (ProductImageDTO) obj;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		return true;
	}

}
