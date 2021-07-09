package com.nhattan.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhattan.ecommerce.dto.OrderDTO;
import com.nhattan.ecommerce.dto.ProductRatingDTO;
import com.nhattan.ecommerce.service.ICustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@GetMapping(value = "/user/customer/order")
	public List<OrderDTO> showOrderByCustomer(@RequestHeader(name = "Authorization") String token) {
		token = getToken(token);
		return customerService.showOrderByCustomer(token);
	}

	@GetMapping(value = "/user/customer/order/{orderID}")
	public OrderDTO showOrderByCustomer(@PathVariable("orderID") int orderID, @RequestHeader(name = "Authorization") String token) {
		token = getToken(token);
		return customerService.showOneOrder(orderID, token);
	}

	@PostMapping(value = "/user/customer/order")
	public String createOrder(@Valid @RequestBody OrderDTO orderRequest, @RequestHeader(name = "Authorization") String token) {
		token = getToken(token);
		return customerService.saveOrder(orderRequest, token);
	}

	@PostMapping(value = "/user/customer/rating")
	public String createRating(@RequestBody ProductRatingDTO ratingRequest,
			@RequestHeader(name = "Authorization") String token) {
		token = getToken(token);
		return customerService.ratingProduct(ratingRequest, token);
	}

	@DeleteMapping(value = "/user/customer/order/{orderID}")
	public String deleteOrder(@PathVariable("orderID") int orderID,
			@RequestHeader(name = "Authorization") String token) {
		token = getToken(token);
		return customerService.invalidateOrder(orderID, token);
	}

	private String getToken(String header) {
		return header.substring(7, header.length());
	}
}
