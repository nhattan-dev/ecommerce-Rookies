package com.nhattan.ecommerce.request;

import org.hibernate.validator.constraints.NotBlank;

public class ProductColorRequest {
	@NotBlank(message = "cannot-be-empty")
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof ProductColorRequest))
			return false;
		return ((ProductColorRequest) obj).getColor().equalsIgnoreCase(this.color);
	}
}
