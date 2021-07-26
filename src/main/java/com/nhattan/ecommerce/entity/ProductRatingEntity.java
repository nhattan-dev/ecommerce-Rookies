package com.nhattan.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ProductRating", uniqueConstraints = { @UniqueConstraint(columnNames = { "customerID", "productID" }) })
public class ProductRatingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productRatingID")
	private int productRatingID = 0;

	@Column(name = "point", nullable = false)
	private int point;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerID", nullable = false)
	private CustomerEntity customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productID", nullable = false)
	private ProductEntity product;
}
