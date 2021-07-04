package com.nhattan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhattan.ecommerce.entity.RoleEntity;

public interface IRoleRepository extends JpaRepository<RoleEntity, Integer>{
	RoleEntity findByRoleLike(String role);
}
