package com.nhattan.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhattan.ecommerce.dto.ProductRatingDTO;
import com.nhattan.ecommerce.request.CreateOrderRequest;
import com.nhattan.ecommerce.response.ReadOrderResponse;
import com.nhattan.ecommerce.service.ICustomerService;

@RestController
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@GetMapping(value = "customer/order/{customerID}")
	public List<ReadOrderResponse> showOrderByCustomer(@PathVariable("customerID") int customerID) {
		return customerService.showOrderByCustomerID(customerID);
	}

	@PostMapping(value = "customer/order")
	public String createOrder(@RequestBody CreateOrderRequest orderRequest) {
		return customerService.saveOrder(orderRequest);
	}

	@PostMapping(value = "customer/rating")
	public String createOrder(@RequestBody ProductRatingDTO ratingRequest) {
		return customerService.ratingProduct(ratingRequest);
	}

	@DeleteMapping(value = "customer/order/{orderID}")
	public String deleteOrder(@PathVariable("orderID") int orderID) {
		return customerService.deleteOrder(orderID);
	}
}
