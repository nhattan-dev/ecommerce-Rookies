package com.nhattan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhattan.ecommerce.entity.ProductImageEntity;

public interface IProductImageRepository extends JpaRepository<ProductImageEntity, Integer>{
	ProductImageEntity findProductImageByImagePath(String imagePath);
}
