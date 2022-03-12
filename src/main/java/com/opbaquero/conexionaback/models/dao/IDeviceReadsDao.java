package com.opbaquero.conexionaback.models.dao;

import com.opbaquero.conexionaback.models.entity.Device;
import com.opbaquero.conexionaback.models.entity.Read;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import com.opbaquero.conexionaback.models.service.dto.DeviceReadDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IDeviceReadsDao extends JpaRepository<Read, UUID> {

    List<Read> findByDevice(Device device);

    @Query("SELECT NEW  com.opbaquero.conexionaback.models.service.dto.DeviceReadDTO(r.id, r.dateRead, p.nameProduct, r.device) " +
            "FROM Read r " +
            "INNER JOIN r.wareHouseProduct wp " +
            "INNER JOIN wp.product p " +
            "WHERE wp.warehouse = ?1 " +
            "ORDER BY r.dateRead DESC")
    List<DeviceReadDTO> findReadsByWarehouseWithProductName(Warehouse warehouse);

}
