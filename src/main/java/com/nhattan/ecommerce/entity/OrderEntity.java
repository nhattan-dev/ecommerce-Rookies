package com.nhattan.ecommerce.entity;

import com.nhattan.ecommerce.enums.ORDER_STATUS;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Orders")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderID")
	private int orderID = 0;

	@Column(name = "orderCode", unique = true, nullable = false)
	private String orderCode;

	@Column(name = "transactStatus")
	private String transactStatus = ORDER_STATUS.UNCONFIRMED.name();

	@Column(name = "orderDate", nullable = false)
	private Date orderDate = new Date();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerID", nullable = false)
	private CustomerEntity customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "addressID", nullable = false)
	private AddressEntity address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = {CascadeType.REMOVE})
	private List<OrderDetailEntity> orderDetails = new ArrayList<>();
}
