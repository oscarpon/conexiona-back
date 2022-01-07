package com.opbaquero.conexionaback.models.service.impl;

import com.opbaquero.conexionaback.models.dao.IBuildingDao;
import com.opbaquero.conexionaback.models.entity.Building;
import com.opbaquero.conexionaback.models.entity.Hospital;
import com.opbaquero.conexionaback.models.service.interfaces.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BuildingServiceImpl implements IBuildingService {

    @Autowired
    private IBuildingDao buildingDao;

    @Override
    public List<Building> findByHospital(Hospital hospital) {
        return buildingDao.findByHospital(hospital);
    }

    @Override
    public Building save(Building building) {
        return buildingDao.save(building);
    }

    @Override
    public Building findOne(UUID id) {
        System.out.println(id);
        return buildingDao.findById(id).orElse(null);
    }

    @Override
    public void delete(UUID id) {
        buildingDao.deleteById(id);
    }

    @Override
    public List<Building> findAll(){
        return buildingDao.findAll();
    }

   @Override
    public List<Building> fetchBuildingByIdWithHospitalWithAccount(UUID id) {
        return buildingDao.fetchBuildingByIdWithHospitalWithAccount(id);
    }


}
