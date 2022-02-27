package com.opbaquero.conexionaback.models.dao;

import com.opbaquero.conexionaback.models.entity.Device;
import com.opbaquero.conexionaback.models.entity.Read;
import com.opbaquero.conexionaback.models.service.dto.DeviceReadDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IDeviceReadsDao extends JpaRepository<Read, UUID> {

    List<Read> findByDevice(Device device);

    @Query("SELECT NEW  com.opbaquero.conexionaback.models.service.dto.DeviceReadDTO(r.id, p.nameProduct, r.device)" +
            "FROM Read r " +
            "INNER JOIN WareHouseProduct wp " +
            "INNER JOIN Products p " +
            "WHERE wp.warehouse = ?1")
    List<DeviceReadDTO> findReadsByWarehouseWithProductName(UUID warehouseId);

}
