package com.opbaquero.conexionaback.models.dao;

import com.opbaquero.conexionaback.models.entity.Replacement;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IReplacementDao extends JpaRepository<Replacement, UUID> {

    @Query("select r from Replacement r join fetch r.user u join fetch r.replacementItems l join fetch l.product where r.id=?1")
    public Replacement fetchByIdWithUserWithReplacementItemWithProducts(UUID id);

    List<Replacement> findByWarehouse(Warehouse warehouse);

}
