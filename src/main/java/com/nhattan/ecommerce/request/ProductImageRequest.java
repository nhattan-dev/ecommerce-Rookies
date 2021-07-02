package com.nhattan.ecommerce.request;

import org.hibernate.validator.constraints.NotBlank;

public class ProductImageRequest {
	@NotBlank(message = "cannot-be-empty")
	private String imagePath;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof ProductImageRequest))
			return false;
		return ((ProductImageRequest) obj).getImagePath().equalsIgnoreCase(this.imagePath);
	}
}
