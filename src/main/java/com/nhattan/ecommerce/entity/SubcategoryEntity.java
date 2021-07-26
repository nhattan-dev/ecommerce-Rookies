package com.nhattan.ecommerce.entity;

import com.nhattan.ecommerce.enums.STATUS;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Subcategory")
@Getter
@Setter
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

	@Column(name = "status")
	private String status = STATUS.NOT_AVAILABLE.name();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryID", nullable = false)
	private CategoryEntity category;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subcategory")
	private List<ProductEntity> products = new ArrayList<ProductEntity>();

	public SubcategoryEntity() {
	}

	public SubcategoryEntity(int subcategoryID, String subcategoryCode, String subcategoryName, String description, String status, CategoryEntity category) {
		this.subcategoryID = subcategoryID;
		this.subcategoryCode = subcategoryCode;
		this.subcategoryName = subcategoryName;
		this.description = description;
		this.status = status;
		this.category = category;
	}
}
