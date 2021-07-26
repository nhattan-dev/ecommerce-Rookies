package com.nhattan.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Customer")
public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerID")
	private int customerID = 0;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private List<AddressEntity> addresses = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private List<OrderEntity> orders = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private List<ProductRatingEntity> productRatings = new ArrayList<>();

}
