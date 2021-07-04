package com.nhattan.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ProductRating", uniqueConstraints = { @UniqueConstraint(columnNames = { "customerID", "productID" }) })
public class ProductRatingEntity {
//	@EmbeddedId
//	private ProductRatingID productRatingID;

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

//	public ProductRatingID getProductRatingID() {
//		return productRatingID;
//	}
//
//	public void setProductRatingID(ProductRatingID productRatingID) {
//		this.productRatingID = productRatingID;
//	}

	public int getPoint() {
		return point;
	}

	public int getProductRatingID() {
		return productRatingID;
	}

	public void setProductRatingID(int productRatingID) {
		this.productRatingID = productRatingID;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}
}
