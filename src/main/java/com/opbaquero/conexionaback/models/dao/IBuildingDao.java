package com.opbaquero.conexionaback.models.dao;

import com.opbaquero.conexionaback.models.entity.Building;
import com.opbaquero.conexionaback.models.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IBuildingDao extends JpaRepository<Building, UUID> {

    List<Building> findByHospital(Hospital hospital);

    Optional<Building> findById(UUID id);

    void deleteById(UUID id);

    @Query("select b from Building b join Hospital h on h.id = b.hospital where h.account.id like ?1 ")
    public List<Building> fetchBuildingByIdWithHospitalWithAccount(UUID accountId);

}
