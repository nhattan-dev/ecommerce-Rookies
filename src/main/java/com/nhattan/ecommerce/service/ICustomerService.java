package com.nhattan.ecommerce.service;

import java.util.List;

import com.nhattan.ecommerce.dto.AddressDTO;
import com.nhattan.ecommerce.dto.OrderDTO;
import com.nhattan.ecommerce.dto.ProductRatingDTO;

public interface ICustomerService {
	List<AddressDTO> showAddressByCustomer(String token);

	List<OrderDTO> showOrderByCustomer(String token);

	OrderDTO showOneOrder(int orderID, String token);

	String saveOrder(OrderDTO orderRequest, String token);

	String invalidateOrder(int orderID, String token);

	String ratingProduct(ProductRatingDTO productRating, String token);
}
