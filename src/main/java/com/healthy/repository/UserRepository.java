package com.healthy.repository;

import com.healthy.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    //boolean existsByEmail(String email);

    Optional<User> findByUserName(String userName);

    boolean existsByUserName(String userName);

}