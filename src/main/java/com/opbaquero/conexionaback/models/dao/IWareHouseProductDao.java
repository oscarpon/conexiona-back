package com.opbaquero.conexionaback.models.dao;

import com.opbaquero.conexionaback.models.entity.WareHouseProduct;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWareHouseProductDao extends JpaRepository<WareHouseProduct, UUID> {

    @Query("select w from WareHouseProduct w where w.id = ?1")
    Optional<WareHouseProduct> findById(UUID id);

    List<WareHouseProduct> findByWarehouse(Warehouse warehouse);

    void deleteById(UUID id);

    @Query("select wp.id from WareHouseProduct wp where wp.warehouse.id = ?1 and wp.product.id = ?2")
    UUID findWareHouseProductByWareHouseAndProduct(UUID warehouseId, UUID productId);

}
