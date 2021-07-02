package com.nhattan.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRatingDTO {
	int productRatingID;
	int point;
	int productID;
	int customerID;

	public int getProductRatingID() {
		return productRatingID;
	}

	public void setProductRatingID(int productRatingID) {
		this.productRatingID = productRatingID;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
}
