package org.springboot.busreservationapi.repository;

import org.springboot.busreservationapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.userPhoneNo = :phone AND u.userPassword = :password")
    Optional<User> findByPhoneAndPassword(long phone, String password);

    @Query("SELECT u FROM User u WHERE u.userEmail = :email AND u.userPassword = :password")
    Optional<User> findByEmailAndPassword(String email, String password);
}
