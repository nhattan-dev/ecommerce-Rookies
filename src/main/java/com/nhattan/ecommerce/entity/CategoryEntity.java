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
	private int deleted = 1;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "category")
	private List<SubcategoryEntity> subcategories = new ArrayList<SubcategoryEntity>();

	public CategoryEntity(int categoryID) {
		super();
		this.categoryID = categoryID;
	}

	public CategoryEntity() {
	}

	public CategoryEntity(int categoryID, String categoryCode, String categoryName, String description,
			String imagePath, int deleted) {
		super();
		this.categoryID = categoryID;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.description = description;
		this.imagePath = imagePath;
		this.deleted = deleted;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryCode == null) ? 0 : categoryCode.hashCode());
		result = prime * result + categoryID;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + deleted;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
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
		CategoryEntity other = (CategoryEntity) obj;
		if (categoryCode == null) {
			if (other.categoryCode != null)
				return false;
		} else if (!categoryCode.equals(other.categoryCode))
			return false;
		if (categoryID != other.categoryID)
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (deleted != other.deleted)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		return true;
	}
}
