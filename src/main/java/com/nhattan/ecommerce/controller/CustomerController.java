package com.nhattan.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhattan.ecommerce.dto.ProductRatingDTO;
import com.nhattan.ecommerce.request.CreateOrderRequest;
import com.nhattan.ecommerce.response.ReadOrderResponse;
import com.nhattan.ecommerce.service.ICustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@GetMapping(value = "/user/customer/order/{customerID}")
	public List<ReadOrderResponse> showOrderByCustomer(@PathVariable("customerID") int customerID) {
		return customerService.showOrderByCustomerID(customerID);
	}

	@PostMapping(value = "/user/customer/order")
	public String createOrder(@RequestBody CreateOrderRequest orderRequest) {
		return customerService.saveOrder(orderRequest);
	}

	@PostMapping(value = "/user/customer/rating")
	public String createRating(@RequestBody ProductRatingDTO ratingRequest) {
		return customerService.ratingProduct(ratingRequest);
	}

	@DeleteMapping(value = "/user/customer/order/{orderID}")
	public String deleteOrder(@PathVariable("orderID") int orderID) {
		return customerService.deleteOrder(orderID);
	}
}
