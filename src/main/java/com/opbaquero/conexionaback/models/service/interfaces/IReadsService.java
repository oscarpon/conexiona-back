package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.entity.Device;
import com.opbaquero.conexionaback.models.entity.Read;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import com.opbaquero.conexionaback.models.service.dto.DeviceReadDTO;

import java.util.List;
import java.util.UUID;

public interface IReadsService {

    Read save(Read read);

    List<Read> findByDevice(Device device);

    List<DeviceReadDTO> findReadsWithProductName(Warehouse warehouseId);


}
