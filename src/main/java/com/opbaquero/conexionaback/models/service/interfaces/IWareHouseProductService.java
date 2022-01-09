package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.entity.WareHouseProduct;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import com.opbaquero.conexionaback.models.exceptions.ProductAlreadyInWarehouseException;

import java.util.List;
import java.util.UUID;

public interface IWareHouseProductService {

    List<WareHouseProduct> findAll();

    WareHouseProduct save(WareHouseProduct wareHouseProduct) throws ProductAlreadyInWarehouseException;

    WareHouseProduct findOne(UUID id);

    void delete(UUID id);

    List<WareHouseProduct> findByWareHouse(Warehouse warehouse);

    UUID findWareHouseProductByIds(UUID wareHouseProduct, UUID productId);

}
