package com.nhattan.ecommerce.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "Category")
@Entity
public class CategoryEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryID")
	private int categoryID = 0;

	@Column(name = "categoryCode", unique = true, nullable = false)
	private String categoryCode;

	@Column(name = "categoryName", nullable = false)
	private String categoryName;

	@Column(name = "description")
	private String description;

	@Column(name = "imagePath", nullable = false)
	private String imagePath;

	@Column(name = "deleted")
	private int deleted = 0;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "category")
	private List<SubcategoryEntity> subcategories = new ArrayList<SubcategoryEntity>();

	public CategoryEntity(int categoryID) {
		super();
		this.categoryID = categoryID;
	}

	public CategoryEntity() {
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public List<SubcategoryEntity> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<SubcategoryEntity> subcategories) {
		this.subcategories = subcategories;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
