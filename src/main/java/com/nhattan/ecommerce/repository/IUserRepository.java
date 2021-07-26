package com.nhattan.ecommerce.repository;

import com.nhattan.ecommerce.entity.UserEntity;
import com.nhattan.ecommerce.enums.ACCOUNT_STATUS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findOneByEmailAndUserIDNot(String email, int userID);

    Boolean existsByEmail(String email);

    Boolean existsByEmailAndStatus(String email, String status);

    Optional<UserEntity> findOneByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByRole_Role(String role);

    @Modifying
    @Query(value = "UPDATE Users SET password = :password WHERE email = :email", nativeQuery = true)
    void updatePasswordByEmail(@Param("email") String email, @Param("password") String password);

    @Modifying
    @Query(value = "UPDATE Users SET status = :status WHERE email = :email", nativeQuery = true)
    void updateStatusByEmail(@Param("status") String status, @Param("email") String email);

    @Modifying
    @Query(value = "UPDATE Users SET status = 'NOT_AVAILABLE' WHERE email = :email", nativeQuery = true)
    void deletedByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "UPDATE Users SET status = 'NOT_AVAILABLE' WHERE userID = :userID", nativeQuery = true)
    void deletedByUserID(@Param("userID") int userID);

    @Modifying
    @Query(value = "UPDATE Users SET phoneNumber = :phoneNumber, firstName = :firstName, lastName = :lastName" +
            ", gender = :gender, dateOfBirth = :dateOfBirth WHERE email = :email", nativeQuery = true)
    void updateByEmail(@Param("phoneNumber") String phoneNumber, @Param("firstName") String firstName,
                       @Param("lastName") String lastName, @Param("gender") String gender,
                       @Param("dateOfBirth") Date dateOfBirth, @Param("email") String email);
}
