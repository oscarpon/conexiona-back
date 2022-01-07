package com.opbaquero.conexionaback.models.service.impl;

import com.opbaquero.conexionaback.models.dao.IWareHouseDao;
import com.opbaquero.conexionaback.models.entity.Building;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import com.opbaquero.conexionaback.models.service.interfaces.IWareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WareHouseServiceImpl implements IWareHouseService {


    @Autowired
    private IWareHouseDao wareHouseDao;

    @Override
    public List<Warehouse> findAll() {
        return (List<Warehouse>) wareHouseDao.findAll();
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return wareHouseDao.save(warehouse);
    }

    @Override
    public Warehouse findOne(UUID id) {
        return wareHouseDao.findById(id).orElse(null);
    }

    @Override
    public void delete(UUID id) {
        wareHouseDao.deleteById(id);
    }

    @Override
    public List<Warehouse> findByBuilding(Building building) {
        return wareHouseDao.findByBuilding(building);
    }

    @Override
    public List<Warehouse> joinWarehouseByIdWithBuildingWithHospitalWithAccount(UUID id) {
        return wareHouseDao.joinWareHouseByIdWithBuildingsWithHospitalsWithAccount(id);
    }


}
