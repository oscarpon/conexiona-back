package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.entity.Device;
import com.opbaquero.conexionaback.models.entity.WareHouseProduct;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import com.opbaquero.conexionaback.models.exceptions.ProductAlreadyInWarehouseException;
import com.opbaquero.conexionaback.models.service.dto.ActualStockDTO;

import java.util.List;
import java.util.UUID;

public interface IWareHouseProductService {

    List<WareHouseProduct> findAll();

    WareHouseProduct save(WareHouseProduct wareHouseProduct) throws ProductAlreadyInWarehouseException;

    WareHouseProduct findOne(UUID id);

    void delete(UUID id);

    List<WareHouseProduct> findByWareHouse(Warehouse warehouse);

    WareHouseProduct findWareHouseProductByIds(UUID wareHouseProduct, UUID productId);

    void update(WareHouseProduct wareHouseProduct);

    List<ActualStockDTO> findActualStockByWareHouse(UUID id);

    void reduceStockOfProduct(Device id);

    void asociateDeviceToProduct(WareHouseProduct wareHouseProduct);

    List<ActualStockDTO> findStockCeroByAccount(UUID accountId);

}
