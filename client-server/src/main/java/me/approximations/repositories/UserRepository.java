package me.approximations.repositories;

import me.approximations.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u LEFT JOIN FETCH u.posts WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);
}
