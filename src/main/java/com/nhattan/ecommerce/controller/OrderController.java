package com.nhattan.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhattan.ecommerce.response.ReadOrderResponse;
import com.nhattan.ecommerce.service.IOrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@GetMapping(value = "/admin/order")
	public List<ReadOrderResponse> showAllOrder() {
		return orderService.showAllOrder();
	}

	@GetMapping(value = "/admin/order/unconfirmed")
	public List<ReadOrderResponse> showOrderUnconfirmed() {
		return orderService.showOrderByUnconfirmed();
	}

	@PutMapping(value = "/admin/order/{orderID}")
	public void confirmOrder(@PathVariable("orderID") int orderID) {
		orderService.confirmOrder(orderID);
	}
}
