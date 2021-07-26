package com.nhattan.ecommerce.dto;

import com.nhattan.ecommerce.entity.OrderDetailEntity;
import com.nhattan.ecommerce.entity.OrderEntity;
import com.nhattan.ecommerce.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

@Getter
@Setter
public class OrderDetailDTO {
	@Min(message = "must-be-greater-than-or-equals-0", value = 0)
	private int orderDetailID;
	@Min(message = "cannot-order-with-product-quantity-least-than-1", value = 1)
	private int quantity;
	@NotBlank(message = "cannot-be-empty")
	private String color;
	private int price;
	@NotBlank(message = "cannot-be-empty")
	private String size;
	@Min(message = "must-be-greater-than-or-equals-0", value = 1)
	private int productID;
	private String productName;

	public static OrderDetailEntity toEntity(OrderDetailDTO dto){
		OrderDetailEntity entity = new OrderDetailEntity();
		entity.setOrderDetailID(dto.getOrderDetailID());
		entity.setColor(dto.getColor());
		entity.setPrice(dto.getPrice());
		entity.setSize(dto.getSize());
		entity.setQuantity(dto.getQuantity());
		ProductEntity p = new ProductEntity();
		p.setProductID(dto.getProductID());
		entity.setProduct(p);
		return entity;
	}

	public static OrderDetailDTO toDTO(OrderDetailEntity entity){
		OrderDetailDTO dto = new OrderDetailDTO();
		dto.setOrderDetailID(entity.getOrderDetailID());
		dto.setColor(entity.getColor());
		dto.setSize(entity.getSize());
		dto.setPrice(entity.getPrice());
		dto.setProductID(entity.getProduct().getProductID());
		dto.setQuantity(entity.getQuantity());
		dto.setProductName(entity.getProduct().getName());
		return dto;
	}
}
