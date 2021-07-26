package com.nhattan.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ProductImage", uniqueConstraints = { @UniqueConstraint(columnNames = { "productID", "imagePath" }) })
public class ProductImageEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productImageID")
	private int productImageID = 0;
	
	@Column(name = "imagePath", nullable = false)
	private String imagePath;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productID", nullable = false)
	private ProductEntity product;
}
