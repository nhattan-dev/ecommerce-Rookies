package com.nhattan.ecommerce.service;

import com.nhattan.ecommerce.dto.OrderDTO;

import java.util.List;

public interface IOrderService {
	void confirmOrder(int orderID);

	List<OrderDTO> showAllOrder();

	List<OrderDTO> showOrderByUnconfirmed();

	OrderDTO showOne(int orderID);
}
