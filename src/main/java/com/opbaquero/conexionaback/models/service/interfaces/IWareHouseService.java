package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.entity.Building;
import com.opbaquero.conexionaback.models.entity.Warehouse;

import java.util.List;
import java.util.UUID;

public interface IWareHouseService {

    public List<Warehouse> findAll();

    public Warehouse save(Warehouse warehouse);

    public Warehouse findOne(UUID id);

    public void delete(UUID id);

    public List<Warehouse> findByBuilding(Building building);

    public List<Warehouse> joinWarehouseByIdWithBuildingWithHospitalWithAccount(UUID id);

}
