package com.opbaquero.conexionaback.models.service.impl;

import com.opbaquero.conexionaback.models.dao.IDeviceReadsDao;
import com.opbaquero.conexionaback.models.entity.Device;
import com.opbaquero.conexionaback.models.entity.Read;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import com.opbaquero.conexionaback.models.service.dto.DeviceReadDTO;
import com.opbaquero.conexionaback.models.service.interfaces.IReadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReadsServiceImpl implements IReadsService {

    @Autowired
    private IDeviceReadsDao deviceReadsDao;

    @Override
    public Read save(Read read) {
        return deviceReadsDao.save(read);
    }

    @Override
    public List<Read> findByDevice(Device device) {
        return deviceReadsDao.findByDevice(device);
    }

    @Override
    public List<DeviceReadDTO> findReadsWithProductName(Warehouse warehouse) {
        return deviceReadsDao.findReadsByWarehouseWithProductName(warehouse);
    }
}
