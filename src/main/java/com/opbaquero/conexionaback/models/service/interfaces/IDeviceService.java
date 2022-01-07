package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.entity.Device;

import java.util.List;
import java.util.UUID;

public interface IDeviceService {

    public List<Device> findAll();

    public Device save(Device device);

    public Device findOne(UUID id);

    public void delete(UUID id);

    //findByWareHouse(WareHouse warehouse)

}
