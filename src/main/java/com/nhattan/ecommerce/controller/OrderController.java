package com.nhattan.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhattan.ecommerce.dto.OrderDTO;
import com.nhattan.ecommerce.service.IOrderService;

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

	@PutMapping(value = "/admin/order/{orderID}")
	public void confirmOrder(@PathVariable("orderID") int orderID) {
		orderService.confirmOrder(orderID);
	}
}
