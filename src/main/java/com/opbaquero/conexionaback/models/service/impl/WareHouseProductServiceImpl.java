package com.opbaquero.conexionaback.models.service.impl;

import com.opbaquero.conexionaback.models.dao.IWareHouseProductDao;
import com.opbaquero.conexionaback.models.entity.WareHouseProduct;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import com.opbaquero.conexionaback.models.service.interfaces.IWareHouseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WareHouseProductServiceImpl implements IWareHouseProductService {

    @Autowired
    private IWareHouseProductDao wareHouseProductDao;


    @Override
    public List<WareHouseProduct> findAll() {
        return wareHouseProductDao.findAll();
    }

    @Override
    public WareHouseProduct save(WareHouseProduct wareHouseProduct) {
        return wareHouseProductDao.save(wareHouseProduct);
    }

    @Override
    public WareHouseProduct findOne(UUID id) {
        WareHouseProduct wareHouseProduct = wareHouseProductDao.findById(id).orElse(null);
        return wareHouseProduct;
    }

    @Override
    public void delete(UUID id) {
        wareHouseProductDao.deleteById(id);
    }

    @Override
    public List<WareHouseProduct> findByWareHouse(Warehouse warehouse) {
        return wareHouseProductDao.findByWarehouse(warehouse);
    }
}
