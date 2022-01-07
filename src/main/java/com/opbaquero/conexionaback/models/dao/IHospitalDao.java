package com.opbaquero.conexionaback.models.dao;

import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.models.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IHospitalDao extends JpaRepository<Hospital, UUID> {

    List<Hospital> findByAccount(Account account);

    Optional<Hospital> findById(UUID id);

    void deleteById(UUID id);
}
