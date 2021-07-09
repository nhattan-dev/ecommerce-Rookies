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
	private int deleted = 1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryID", nullable = false)
	private CategoryEntity category;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "subcategory")
	private List<ProductEntity> products = new ArrayList<ProductEntity>();

	public SubcategoryEntity() {
		super();
	}

	public SubcategoryEntity(int subcategoryID) {
		super();
		this.subcategoryID = subcategoryID;
	}

	public SubcategoryEntity(int subcategoryID, String subcategoryCode, String subcategoryName, String description,
			CategoryEntity category) {
		super();
		this.subcategoryID = subcategoryID;
		this.subcategoryCode = subcategoryCode;
		this.subcategoryName = subcategoryName;
		this.description = description;
		this.category = category;
	}

	public SubcategoryEntity(int subcategoryID, String subcategoryName, String description, CategoryEntity category) {
		super();
		this.subcategoryID = subcategoryID;
		this.subcategoryName = subcategoryName;
		this.description = description;
		this.category = category;
	}

	public SubcategoryEntity(int subcategoryID, String subcategoryCode, String subcategoryName, String description,
			int deleted, CategoryEntity category) {
		super();
		this.subcategoryID = subcategoryID;
		this.subcategoryCode = subcategoryCode;
		this.subcategoryName = subcategoryName;
		this.description = description;
		this.deleted = deleted;
		this.category = category;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + deleted;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((subcategoryCode == null) ? 0 : subcategoryCode.hashCode());
		result = prime * result + subcategoryID;
		result = prime * result + ((subcategoryName == null) ? 0 : subcategoryName.hashCode());
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
		SubcategoryEntity other = (SubcategoryEntity) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (category.getCategoryID() != other.category.getCategoryID())
			return false;
		if (deleted != other.deleted)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (subcategoryCode == null) {
			if (other.subcategoryCode != null)
				return false;
		} else if (!subcategoryCode.equals(other.subcategoryCode))
			return false;
		if (subcategoryID != other.subcategoryID)
			return false;
		if (subcategoryName == null) {
			if (other.subcategoryName != null)
				return false;
		} else if (!subcategoryName.equals(other.subcategoryName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubcategoryEntity [subcategoryID=" + subcategoryID + ", subcategoryCode=" + subcategoryCode
				+ ", subcategoryName=" + subcategoryName + ", description=" + description + ", deleted=" + deleted
				+ ", category=" + category + "]";
	}

}
