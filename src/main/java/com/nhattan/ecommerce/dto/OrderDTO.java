package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.AddressEntity;
import com.nhattan.ecommerce.entity.CustomerEntity;
import com.nhattan.ecommerce.entity.OrderEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDTO {
    @Min(message = "must-be-greater-than-or-equals-0", value = 0)
    private int orderID;
    @Min(message = "must-be-greater-than-or-equals-1", value = 1)
    private int addressID;
    private String orderCode;
    private String transactStatus;
    private Date orderDate = new Date();
    @Min(message = "must-be-greater-than-or-equals-0", value = 0)
    private int customerID;
    @Size(min = 1, message = "cannot-order-with-no-product")
    private List<OrderDetailDTO> orderDetails = new ArrayList<OrderDetailDTO>();

    public static OrderEntity toEntity(OrderDTO dto) {
		OrderEntity order = new OrderEntity();
		order.setOrderID(dto.getOrderID());
        AddressEntity address = new AddressEntity();
        address.setAddressID(dto.getAddressID());
		order.setAddress(address);
		return order;
	}

	public static OrderDTO toDTO(OrderEntity entity){
        OrderDTO dto = new OrderDTO();
        dto.setAddressID(entity.getAddress().getAddressID());
        dto.setOrderCode(entity.getOrderCode());
        dto.setOrderDate(entity.getOrderDate());
        dto.setOrderID(entity.getOrderID());
        dto.setTransactStatus(entity.getTransactStatus());
        dto.setCustomerID(entity.getCustomer().getCustomerID());
        dto.setOrderDetails(entity.getOrderDetails().stream().map(OrderDetailDTO::toDTO).collect(Collectors.toList()));
        return dto;
    }
}
