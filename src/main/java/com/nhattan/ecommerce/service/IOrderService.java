package com.nhattan.ecommerce.service;

import java.util.List;

import com.nhattan.ecommerce.dto.OrderDTO;

public interface IOrderService {
	void confirmOrder(int orderID);

	List<OrderDTO> showAllOrder();

	List<OrderDTO> showOrderByUnconfirmed();
}
