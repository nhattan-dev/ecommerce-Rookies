package com.nhattan.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ProductSize", uniqueConstraints = { @UniqueConstraint(columnNames = { "productID", "size" }) })
public class ProductSizeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productSizeID")
	private int productSizeID = 0;

	@Column(name = "size", nullable = false)
	private String size;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productID", nullable = false)
	private ProductEntity product;
}
