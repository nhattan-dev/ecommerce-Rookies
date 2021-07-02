package com.nhattan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhattan.ecommerce.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findOneByEmailAndPasswordAndDeleted(String email, String password, int value);

	UserEntity findOneByEmailAndUserIDNot(String email, int userID);

	UserEntity findOneByEmail(String email);
	
	@Modifying
	@Query(value = "UPDATE Users SET password = :password WHERE email = :email", nativeQuery = true)
	void updatePasswordByEmail(@Param("email") String email, @Param("password") String password);

	@Modifying
	@Query(value = "UPDATE Users SET deleted = 1 WHERE userID = :userID", nativeQuery = true)
	void deletedByUserID(@Param("userID") int userID);

	@Modifying
	@Query(value = "UPDATE Users SET email = :email, phoneNumber = :phoneNumber, firstName = :firstName, "
			+ "lastName = :lastName, gender = :gender, dateOfBirth = :dateOfBirth WHERE userID = :userID", nativeQuery = true)
	void updateByUserID(@Param("email") String email, @Param("phoneNumber") String phoneNumber,
			@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("gender") int gender,
			@Param("dateOfBirth") String dateOfBirth, @Param("userID") int userID);
}
