package com.nhattan.ecommerce.service.impl;

import com.nhattan.ecommerce.dto.AddressDTO;
import com.nhattan.ecommerce.dto.OrderDTO;
import com.nhattan.ecommerce.dto.OrderDetailDTO;
import com.nhattan.ecommerce.entity.OrderEntity;
import com.nhattan.ecommerce.enums.ORDER_STATUS;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.IOrderRepository;
import com.nhattan.ecommerce.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Transactional
    @Override
    public void confirmOrder(int orderID) {
        if (!orderRepository.existsById(orderID))
            throw new NotFoundException("order-not-found");
        orderRepository.updateTransactStutusByOrderID(ORDER_STATUS.CONFIRMED.name(), orderID);
    }

    @Override
    public List<OrderDTO> showAllOrder() {
        return orderRepository.findAll().stream().map(x -> OrderDTO.toDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> showOrderByUnconfirmed() {
        return orderRepository.findOrderByTransactStatus(ORDER_STATUS.UNCONFIRMED.name()).stream()
                .map(x -> OrderDTO.toDTO(x)).collect(Collectors.toList());
    }

    @Override
    public OrderDTO showOne(int orderID) {
        OrderEntity entity = orderRepository.findById(orderID).orElseThrow(() -> new NotFoundException("order-not-found"));
        OrderDTO dto = OrderDTO.toDTO(entity);
        dto.setOrderDetails(entity.getOrderDetails().stream().map(e -> OrderDetailDTO.toDTO(e)).collect(Collectors.toList()));
        return dto;
    }
}
