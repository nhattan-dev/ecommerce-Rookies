package com.nhattan.ecommerce.entity;

import com.nhattan.ecommerce.enums.STATUS;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "Category")
@Entity
@Getter
@Setter
public class CategoryEntity implements Serializable {
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

    @Column(name = "status", nullable = false)
    private String status = STATUS.NOT_AVAILABLE.name();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<SubcategoryEntity> subcategories = new HashSet<>();

    public CategoryEntity() {
    }

    public CategoryEntity(int categoryID, String categoryCode, String categoryName, String description, String imagePath, String status) {
        this.categoryID = categoryID;
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.description = description;
        this.imagePath = imagePath;
        this.status = status;
    }
}
