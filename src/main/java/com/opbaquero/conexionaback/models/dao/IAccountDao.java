package com.opbaquero.conexionaback.models.dao;

import com.opbaquero.conexionaback.models.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IAccountDao extends JpaRepository<Account, UUID> {

    Optional<Account> findById(UUID id);

    void deleteById(UUID id);

    boolean existsByAccountName(String accountName);

    UUID findByAccountName(String accountName);


}
