package com.nhattan.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "OrderDetail", uniqueConstraints = { @UniqueConstraint(columnNames = { "productID", "orderID" }) })
public class OrderDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderDetailID")
	private int orderDetailID = 0;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@Column(name = "color", nullable = false)
	private String color;

	@Column(name = "price", nullable = false)
	private int price;

	@Column(name = "size", nullable = false)
	private String size;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productID", nullable = false)
	private ProductEntity product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderID", nullable = false)
	private OrderEntity order;
}
