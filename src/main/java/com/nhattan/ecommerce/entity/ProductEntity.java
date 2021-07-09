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
	private int deleted = 1;

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

	public ProductEntity(int productID, String name, String description, int quantity, SubcategoryEntity subcategory) {
		super();
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.subcategory = subcategory;
	}

	public ProductEntity(int productID, String name, String description, int point, int deleted, int quantity,
			SubcategoryEntity subcategory) {
		super();
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.point = point;
		this.deleted = deleted;
		this.quantity = quantity;
		this.subcategory = subcategory;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deleted;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orderDetails == null) ? 0 : orderDetails.hashCode());
		result = prime * result + point;
		result = prime * result + productID;
		result = prime * result + quantity;
		result = prime * result + ((subcategory == null) ? 0 : subcategory.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductEntity other = (ProductEntity) obj;
		if (deleted != other.deleted)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orderDetails == null) {
			if (other.orderDetails != null)
				return false;
		} else if (!orderDetails.equals(other.orderDetails))
			return false;
		if (point != other.point)
			return false;
		if (productID != other.productID)
			return false;
		if (quantity != other.quantity)
			return false;
		if (subcategory == null) {
			if (other.subcategory != null)
				return false;
		} else if (subcategory.getSubcategoryID() != other.subcategory.getSubcategoryID())
			return false;
		return true;
	}
}
