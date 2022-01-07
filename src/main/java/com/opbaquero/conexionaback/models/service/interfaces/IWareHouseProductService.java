package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.entity.WareHouseProduct;
import com.opbaquero.conexionaback.models.entity.Warehouse;

import java.util.List;
import java.util.UUID;

public interface IWareHouseProductService {
    public List<WareHouseProduct> findAll();

    public WareHouseProduct save(WareHouseProduct wareHouseProduct);

    WareHouseProduct findOne(UUID id);

    void delete(UUID id);

    public List<WareHouseProduct> findByWareHouse(Warehouse warehouse);

}
