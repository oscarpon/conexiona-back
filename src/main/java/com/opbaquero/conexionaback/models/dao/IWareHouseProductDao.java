package com.opbaquero.conexionaback.models.dao;

import com.opbaquero.conexionaback.models.entity.WareHouseProduct;
import com.opbaquero.conexionaback.models.entity.Warehouse;
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

}
