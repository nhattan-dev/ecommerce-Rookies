package com.nhattan.ecommerce.service;

import java.util.List;

import com.nhattan.ecommerce.dto.ProductRatingDTO;
import com.nhattan.ecommerce.request.CreateOrderRequest;
import com.nhattan.ecommerce.response.ReadAddressResponse;
import com.nhattan.ecommerce.response.ReadOrderResponse;

public interface ICustomerService {
	List<ReadAddressResponse> showAddressByCustomerID(int customerID);
	List<ReadOrderResponse> showOrderByCustomerID(int customerID);
	String saveOrder(CreateOrderRequest orderRequest);
	String deleteOrder(int orderID);
	String ratingProduct(ProductRatingDTO productRating);
}
