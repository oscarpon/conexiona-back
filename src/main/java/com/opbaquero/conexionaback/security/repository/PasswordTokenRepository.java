package com.opbaquero.conexionaback.security.repository;

import com.opbaquero.conexionaback.security.entity.PasswordReset;
import com.opbaquero.conexionaback.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordReset, UUID> {
}
