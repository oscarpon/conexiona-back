package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.entity.Device;
import com.opbaquero.conexionaback.models.entity.Warehouse;

import java.util.List;
import java.util.UUID;

public interface IDeviceService {

    List<Device> findAll();

    Device save(Device device);

    Device findOne(UUID id);

    void delete(UUID id);

    List<Device> findByWareHouse(Warehouse warehouse);

}
