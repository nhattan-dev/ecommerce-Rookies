package com.nhattan.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhattan.ecommerce.entity.ProductSizeEntity;

public interface IProductSizeRepository extends JpaRepository<ProductSizeEntity, Integer>{
	Optional<ProductSizeEntity> findProductSizeBySize(String size);
}	
