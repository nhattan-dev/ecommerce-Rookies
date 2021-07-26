package com.nhattan.ecommerce.controller;

import com.nhattan.ecommerce.dto.OrderDTO;
import com.nhattan.ecommerce.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@GetMapping(value = "/admin/order")
	public List<OrderDTO> showAllOrder(
			@RequestParam(name = "key", required = false, defaultValue = "all") String key) {
		String showAllValue = "unconfirmed";
		if (showAllValue.equalsIgnoreCase(key))
			return orderService.showOrderByUnconfirmed();
		else
			return orderService.showAllOrder();
	}

	@GetMapping(value = "/admin/order/{orderID}")
	public ResponseEntity<OrderDTO> showOne(@PathVariable("orderID") int orderID){
		return ResponseEntity.ok().body(orderService.showOne(orderID));
	}

	@PatchMapping(value = "/admin/order/{orderID}")
	public void confirmOrder(@PathVariable("orderID") int orderID) {
		orderService.confirmOrder(orderID);
	}
}
