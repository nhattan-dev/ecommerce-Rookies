package com.nhattan.ecommerce.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class ProductSizeDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productSizeID;
	@NotBlank(message = "cannot-be-empty")
	private String size;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productID;

	public ProductSizeDTO() {
		super();
	}

	public ProductSizeDTO(int productSizeID, String size) {
		super();
		this.productSizeID = productSizeID;
		this.size = size;
	}

	public int getProductSizeID() {
		return productSizeID;
	}

	public void setProductSizeID(int productSizeID) {
		this.productSizeID = productSizeID;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
		ProductSizeDTO other = (ProductSizeDTO) obj;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}
}
