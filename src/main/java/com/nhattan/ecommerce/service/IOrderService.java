package com.nhattan.ecommerce.service;

import java.util.List;

import com.nhattan.ecommerce.response.ReadOrderResponse;

public interface IOrderService {
	void confirmOrder(int orderID);
	List<ReadOrderResponse> showAllOrder();
	List<ReadOrderResponse> showOrderByUnconfirmed();
}
