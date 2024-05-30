package org.springboot.busreservationapi.repository;

import org.springboot.busreservationapi.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository interface for Admin entity.
 */
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    /**
     * Finds an admin by their phone number and password.
     *
     * @param phone    the phone number of the admin
     * @param password the password of the admin
     * @return an Optional containing the found admin, or an empty Optional if no admin is found
     */
    @Query("SELECT a FROM Admin a WHERE a.adminPhoneNo = :phone AND a.adminPassword = :password")
    Optional<Admin> findByPhoneAndPassword(@Param("phone") long phone, @Param("password") String password);

    /**
     * Finds an admin by their email and password.
     *
     * @param email    the email of the admin
     * @param password the password of the admin
     * @return an Optional containing the found admin, or an empty Optional if no admin is found
     */
    @Query("SELECT a FROM Admin a WHERE a.adminEmailId = :email AND a.adminPassword = :password")
    Optional<Admin> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
