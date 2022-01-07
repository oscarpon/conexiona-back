package com.opbaquero.conexionaback.models.dao;

import com.opbaquero.conexionaback.models.entity.Building;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IWareHouseDao extends JpaRepository<Warehouse, UUID> {

    List<Warehouse> findByBuilding(Building building);
    
    Optional<Warehouse> findById(UUID id);
    
    void deleteById(UUID id);

    @Query("select w from Warehouse w join Building b on b.id = w.building join Hospital h on h.id = b.hospital where h.account.id like ?1")
    public List<Warehouse> joinWareHouseByIdWithBuildingsWithHospitalsWithAccount(UUID accountId);
    
}
