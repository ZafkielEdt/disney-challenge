package com.challenge.disney.security.repository;

import com.challenge.disney.security.entity.UserEntity;
import com.challenge.disney.security.enums.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByUsername(String username);

    Boolean existsUserEntityByUsername(String username);

    Boolean existsUserEntityByRol(Rol rol);
}
