package com.nhattan.ecommerce.controller;

import com.nhattan.ecommerce.dto.AddressDTO;
import com.nhattan.ecommerce.dto.OrderDTO;
import com.nhattan.ecommerce.dto.ProductRatingDTO;
import com.nhattan.ecommerce.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@GetMapping(value = "/user/customer/order")
	public List<OrderDTO> showOrder(@RequestHeader(name = "Authorization") String token) {
		token = getToken(token);
		return customerService.showOrder(token);
	}

	@GetMapping(value = "/user/customer/order/{orderID}")
	public OrderDTO showOneOrder(@PathVariable("orderID") int orderID, @RequestHeader(name = "Authorization") String token) {
		token = getToken(token);
		return customerService.showOneOrder(orderID, token);
	}

	@PostMapping(value = "/user/customer/order")
	public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO dto, @RequestHeader(name = "Authorization") String token) {
		return ResponseEntity.ok().body(customerService.saveOrder(dto, getToken(token)));
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
		return customerService.deleteOrder(orderID, token);
	}

	@GetMapping(value = "/user/customer/address")
	public  List<AddressDTO> showAddress(@RequestHeader(name = "Authorization") String token){
		token = getToken(token);
		return customerService.showAddress(token);
	}

	@GetMapping(value = "/user/customer/address/{addressID}")
	public  AddressDTO showOneAddress(@PathVariable("addressID") int addressID, @RequestHeader(name = "Authorization") String token){
		token = getToken(token);
		return customerService.showOneAddress(addressID, token);
	}

	@PostMapping(value = "/user/customer/address")
	public  AddressDTO createAddress(@Valid @RequestBody AddressDTO dto, @RequestHeader(name = "Authorization") String token){
		token = getToken(token);
		return customerService.createAddress(dto, token);
	}

	@PutMapping(value = "/user/customer/address")
	public  AddressDTO updateAddress(@Valid @RequestBody AddressDTO dto, @RequestHeader(name = "Authorization") String token){
		token = getToken(token);
		return customerService.updateAddress(dto, token);
	}

	@DeleteMapping(value = "/user/customer/address/{addressID}")
	public void deleteAddress(@PathVariable("addressID") int addressID, @RequestHeader(name = "Authorization") String token){
		token = getToken(token);
		customerService.deleteAddress(addressID, token);
	}

	private String getToken(String header) {
		return header.substring(7);
	}
}
