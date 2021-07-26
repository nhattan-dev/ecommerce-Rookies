package com.nhattan.ecommerce.repository;

import com.nhattan.ecommerce.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Integer>{
	Optional<RoleEntity> findOneByRole(String role);
}
