package com.nhattan.ecommerce.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class ProductColorDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productColorID;
	@NotBlank(message = "cannot-be-empty")
	private String color;
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int productID;

	public ProductColorDTO() {
		super();
	}

	public ProductColorDTO(int productColorID, String color) {
		super();
		this.productColorID = productColorID;
		this.color = color;
	}

	public int getProductColorID() {
		return productColorID;
	}

	public void setProductColorID(int productColorID) {
		this.productColorID = productColorID;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
		result = prime * result + ((color == null) ? 0 : color.hashCode());
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
		ProductColorDTO other = (ProductColorDTO) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}

}
