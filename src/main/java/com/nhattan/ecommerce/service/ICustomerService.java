package com.nhattan.ecommerce.service;

import com.nhattan.ecommerce.dto.AddressDTO;
import com.nhattan.ecommerce.dto.OrderDTO;
import com.nhattan.ecommerce.dto.ProductRatingDTO;

import java.util.List;

public interface ICustomerService {
	List<AddressDTO> showAddress(String token);

	AddressDTO showOneAddress(int addressID, String token);

	AddressDTO createAddress(AddressDTO address, String token);

	AddressDTO updateAddress(AddressDTO address, String token);

	void deleteAddress(int addressID, String token);

	List<OrderDTO> showOrder(String token);

	OrderDTO showOneOrder(int orderID, String token);

	OrderDTO saveOrder(OrderDTO orderRequest, String token);

	String deleteOrder(int orderID, String token);

	String ratingProduct(ProductRatingDTO productRating, String token);
}
