package com.nhattan.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.IOrderRepository;
import com.nhattan.ecommerce.response.ReadOrderResponse;
import com.nhattan.ecommerce.service.IOrderService;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	@Override
	public void confirmOrder(int orderID) {
		if (!orderRepository.exists(orderID))
			throw new NotFoundException("order-not-found");

		int ConfirmValue = 0;
		orderRepository.updateTransactStutusByOrderID(ConfirmValue, orderID);
	}

	@Override
	public List<ReadOrderResponse> showAllOrder() {
		return orderRepository.findAll().stream().map(x -> modelMapper.map(x, ReadOrderResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ReadOrderResponse> showOrderByUnconfirmed() {
		int OrderUnconfirmValue = 1;
		return orderRepository.findOrderByTransactStatus(OrderUnconfirmValue).stream() 
				.map(x -> modelMapper.map(x, ReadOrderResponse.class)).collect(Collectors.toList());
	}
}
