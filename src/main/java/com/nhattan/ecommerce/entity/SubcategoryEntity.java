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
@Table(name = "Subcategory")
public class SubcategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subcategoryID")
	private int subcategoryID = 0;

	@Column(name = "subcategoryCode", unique = true, nullable = false)
	private String subcategoryCode;

	@Column(name = "subcategoryName", nullable = false)
	private String subcategoryName;

	@Column(name = "description")
	private String description;

	@Column(name = "deleted")
	private int deleted = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryID", nullable = false)
	private CategoryEntity category;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "subcategory")
	private List<ProductEntity> products = new ArrayList<ProductEntity>();

	public int getSubcategoryID() {
		return subcategoryID;
	}

	public void setSubcategoryID(int subcategoryID) {
		this.subcategoryID = subcategoryID;
	}

	public String getSubcategoryCode() {
		return subcategoryCode;
	}

	public void setSubcategoryCode(String subcategoryCode) {
		this.subcategoryCode = subcategoryCode;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

}
