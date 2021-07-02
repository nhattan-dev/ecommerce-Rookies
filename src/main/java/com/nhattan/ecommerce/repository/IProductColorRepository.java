package com.nhattan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhattan.ecommerce.entity.ProductColorEntity;

public interface IProductColorRepository extends JpaRepository<ProductColorEntity, Integer>{
	Boolean existsProductColorByColorLike(String color);
}
