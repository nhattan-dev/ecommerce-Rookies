package com.nhattan.ecommerce.entity;

import java.util.Date;

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
@Table(name = "ProductPrice", uniqueConstraints = { @UniqueConstraint(columnNames = { "productID", "date" }) })
public class ProductPriceEntity {
//	@EmbeddedId
//	private ProductPriceID productPriceID;

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

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

//	public ProductPriceID getProductPriceID() {
//		return productPriceID;
//	}
//
//	public void setProductPriceID(ProductPriceID productPriceID) {
//		this.productPriceID = productPriceID;
//	}

	public int getProductPriceID() {
		return productPriceID;
	}

	public void setProductPriceID(int productPriceID) {
		this.productPriceID = productPriceID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

}
