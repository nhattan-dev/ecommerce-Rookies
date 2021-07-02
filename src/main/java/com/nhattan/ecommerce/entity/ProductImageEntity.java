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

	public int getProductImageID() {
		return productImageID;
	}

	public void setProductImageID(int productImageID) {
		this.productImageID = productImageID;
	}

	public String getImage() {
		return imagePath;
	}

	public void setImage(String image) {
		this.imagePath = image;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}
}
