package com.opbaquero.conexionaback.models.service.impl;

import com.opbaquero.conexionaback.models.dao.IDeviceDao;
import com.opbaquero.conexionaback.models.entity.Device;
import com.opbaquero.conexionaback.models.service.interfaces.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DeviceServiceImpl implements IDeviceService {
    @Autowired
    private IDeviceDao deviceDao;

    @Override
    @Transactional(readOnly = true)
    public List<Device> findAll() {
        return deviceDao.findAll();
    }

    @Override
    public Device save(Device device) {
        return deviceDao.save(device);
    }

    @Override
    public Device findOne(UUID id) {
        return deviceDao.findById(id).orElse(null);
    }

    @Override
    public void delete(UUID id) {
        deviceDao.deleteById(id);
    }
}
