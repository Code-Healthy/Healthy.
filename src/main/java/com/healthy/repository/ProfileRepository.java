package com.healthy.repository;

import com.healthy.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    boolean existsByUserName(String userName);

    //Método para verificar si existe un perfil con el mismo username, excepto el usuario actual
    boolean existsByUserNameAndIdNot(String userName, Integer userId);
    Optional<Profile> findByUser(Profile profile);
    Optional<Profile> findByUserUserName(String userName);
    boolean existsByUserUserName(String username);


}