package com.nhattan.ecommerce.entity;

import com.nhattan.ecommerce.enums.STATUS;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product")
@Getter
@Setter
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

    @Column(name = "status")
    private String status = STATUS.NOT_AVAILABLE.name();

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategoryID", nullable = false)
    private SubcategoryEntity subcategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductImageEntity> productImages = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductColorEntity> productColors = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductSizeEntity> productSizes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductRatingEntity> productRatings = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();
}
