package com.nhattan.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ProductColor", uniqueConstraints = { @UniqueConstraint(columnNames = { "productID", "color" }) })
public class ProductColorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productColorID")
	private int productColorID = 0;

	@Column(name = "color")
	private String color;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productID", nullable = false)
	private ProductEntity product;
}
