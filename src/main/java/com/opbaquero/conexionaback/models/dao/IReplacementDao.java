package com.opbaquero.conexionaback.models.dao;

import com.opbaquero.conexionaback.models.entity.Replacement;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import com.opbaquero.conexionaback.models.service.dto.ReplacementDataExportDTO;
import com.opbaquero.conexionaback.models.service.dto.ReplacementItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IReplacementDao extends JpaRepository<Replacement, UUID> {

    @Query("select r from Replacement r join fetch r.user u join fetch r.replacementItems l join fetch l.product where r.id=?1")
    Replacement fetchByIdWithUserWithReplacementItemWithProducts(UUID id);

    List<Replacement> findByWarehouse(Warehouse warehouse);

    @Query("SELECT NEW com.opbaquero.conexionaback.models.service.dto.ReplacementDataExportDTO(r.date, u.userName, w.wareHouseName, b.buildingName, h.hospitalName, a.accountName) " +
            "FROM Replacement r " +
            "INNER JOIN r.user u " +
            "INNER JOIN r.warehouse w " +
            "INNER JOIN w.building b " +
            "INNER JOIN b.hospital h " +
            "INNER JOIN h.account a " +
            "WHERE a.id=?1 ORDER BY r.date" )
    List<ReplacementDataExportDTO> findDataRepositionByAccount(UUID id);

    @Query("SELECT NEW com.opbaquero.conexionaback.models.service.dto.ReplacementItemDTO(r.id, ri.quantityReplaced, p.nameProduct) " +
            "FROM Replacement r " +
            "INNER JOIN r.replacementItems ri " +
            "INNER JOIN ri.product wp " +
            "INNER JOIN wp.product p " +
            "WHERE r.id=?1")
    List<ReplacementItemDTO> findItemsByReplacementId(UUID id);

}
