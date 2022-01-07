package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.entity.Building;
import com.opbaquero.conexionaback.models.entity.Hospital;

import java.util.List;
import java.util.UUID;


public interface IBuildingService {

    public List<Building> findByHospital(Hospital hospital);

    public Building save(Building building);

    public Building findOne(UUID id);

    public void delete(UUID id);

    public List<Building> findAll();

    public List<Building> fetchBuildingByIdWithHospitalWithAccount(UUID id);

}
