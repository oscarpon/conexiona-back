package com.opbaquero.conexionaback.models.dao;

import com.opbaquero.conexionaback.models.entity.Device;
import com.opbaquero.conexionaback.models.entity.WareHouseProduct;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import com.opbaquero.conexionaback.models.service.dto.ActualStockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWareHouseProductDao extends JpaRepository<WareHouseProduct, UUID> {

    @Query("select w from WareHouseProduct w where w.id = ?1")
    Optional<WareHouseProduct> findById(UUID id);

    List<WareHouseProduct> findByWarehouse(Warehouse warehouse);

    void deleteById(UUID id);

    @Query("select wp from WareHouseProduct wp where wp.warehouse.id = ?1 and wp.product.id = ?2")
    WareHouseProduct findWareHouseProductByWareHouseAndProduct(UUID warehouseId, UUID productId);

    @Modifying
    @Transactional
    @Query("update WareHouseProduct wp set stock = ?3 where wp.warehouse.id = ?1 and wp.product.id = ?2")
    void updateStock(UUID warehouseId, UUID productId, Integer stock);

    @Query("SELECT NEW com.opbaquero.conexionaback.models.service.dto.ActualStockDTO(w.wareHouseName, p.nameProduct, wp.stock) " +
            "FROM WareHouseProduct wp " +
            "INNER JOIN wp.warehouse w " +
            "INNER JOIN wp.product p " +
            "WHERE w.id=?1")
    List<ActualStockDTO> findActualStockByWareHouse(UUID id);

    @Modifying
    @Transactional
    @Query("update WareHouseProduct wp set wp.stock = wp.stock - 1 where wp.id = ?1")
    void reduceStockOfProduct(UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE WareHouseProduct wp SET wp.device = ?2 WHERE wp.id = ?1")
    void asociateDevideProduct(UUID wareHouseProductId, Device device);

    @Query("SELECT NEW com.opbaquero.conexionaback.models.service.dto.ActualStockDTO(w.wareHouseName, p.nameProduct, wp.stock) " +
            "FROM WareHouseProduct wp " +
            "INNER JOIN wp.warehouse w " +
            "INNER JOIN wp.product p " +
            "INNER JOIN w.building b " +
            "INNER JOIN b.hospital h " +
            "INNER JOIN h.account a " +
            "WHERE a.id=?1 AND wp.stock = 0")
    List<ActualStockDTO> findStockCeroByAccount(UUID id);


}
