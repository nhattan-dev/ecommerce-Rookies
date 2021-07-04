package com.nhattan.ecommerce.request;

import javax.validation.constraints.Min;

public class CreateOrderDetailRequest {
	@Min(value = 0, message = "must-be-greater-than-or-equals-0")
	private int quantity;
	@Min(value = 0, message = "must-be-greater-than-or-equals-0")
	private int productColorID;
	@Min(value = 0, message = "must-be-greater-than-or-equals-0")
	private int productSizeID;
	@Min(value = 0, message = "must-be-greater-than-or-equals-0")
	private int productID;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductColorID() {
		return productColorID;
	}

	public void setProductColorID(int productColorID) {
		this.productColorID = productColorID;
	}

	public int getProductSizeID() {
		return productSizeID;
	}

	public void setProductSizeID(int productSizeID) {
		this.productSizeID = productSizeID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

}
