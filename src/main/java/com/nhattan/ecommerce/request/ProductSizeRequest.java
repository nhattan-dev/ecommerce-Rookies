package com.nhattan.ecommerce.request;

import org.hibernate.validator.constraints.NotBlank;

public class ProductSizeRequest {
	@NotBlank(message = "cannot-be-empty")
	private String size;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof ProductSizeRequest))
			return false;
		return ((ProductSizeRequest) obj).getSize().equalsIgnoreCase(this.size);
	}

}
