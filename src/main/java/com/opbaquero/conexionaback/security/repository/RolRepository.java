package com.opbaquero.conexionaback.security.repository;

import com.opbaquero.conexionaback.security.entity.Rol;
import com.opbaquero.conexionaback.security.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolName(RolName rolName);
}
