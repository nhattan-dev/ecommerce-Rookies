package com.nhattan.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class ProductRatingDTO {
	int productRatingID;
	@Range(min = 1, max = 10, message = "must-be-between-1-and-10")
	int point;
	int productID;
	int customerID;
}
