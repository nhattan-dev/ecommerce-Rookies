package com.nhattan.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "ProductPrice", uniqueConstraints = { @UniqueConstraint(columnNames = { "productID", "date" }) })
public class ProductPriceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productPriceID")
	private int productPriceID = 0;

	@Column(name = "price", nullable = false)
	private long price;

	@Column(name = "date", nullable = false)
	private Date date = new Date();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productID", nullable = false)
	private ProductEntity product;
}
