package com.nhattan.ecommerce.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productID")
	private int productID = 0;

	@Column(name = "productName", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "point")
	private int point = 0;

	@Column(name = "deleted")
	private int deleted = 0;

	@Column(name = "quantity")
	private int quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategoryID", nullable = false)
	private SubcategoryEntity subcategory;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "product")
	private List<ProductImageEntity> productImages = new ArrayList<ProductImageEntity>();

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "product")
	private List<ProductColorEntity> productColors = new ArrayList<ProductColorEntity>();

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "product")
	private List<ProductSizeEntity> productSizes = new ArrayList<ProductSizeEntity>();

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "product")
	private List<ProductRatingEntity> productRatings = new ArrayList<ProductRatingEntity>();

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "product")
	private List<OrderDetailEntity> orderDetails = new ArrayList<OrderDetailEntity>();

	public ProductEntity() {
		super();
	}

	public ProductEntity(int productID) {
		super();
		this.productID = productID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public SubcategoryEntity getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(SubcategoryEntity subcategory) {
		this.subcategory = subcategory;
	}

	public List<ProductImageEntity> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImageEntity> productImages) {
		this.productImages = productImages;
	}

	public List<ProductColorEntity> getProductColors() {
		return productColors;
	}

	public void setProductColors(List<ProductColorEntity> productColors) {
		this.productColors = productColors;
	}

	public List<ProductSizeEntity> getProductSizes() {
		return productSizes;
	}

	public void setProductSizes(List<ProductSizeEntity> productSizes) {
		this.productSizes = productSizes;
	}

	public List<ProductRatingEntity> getProductRatings() {
		return productRatings;
	}

	public void setProductRatings(List<ProductRatingEntity> productRatings) {
		this.productRatings = productRatings;
	}

	public List<OrderDetailEntity> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailEntity> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
