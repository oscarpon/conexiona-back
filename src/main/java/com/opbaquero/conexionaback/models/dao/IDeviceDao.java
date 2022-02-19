package com.opbaquero.conexionaback.models.dao;

import com.opbaquero.conexionaback.models.entity.Device;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDeviceDao extends JpaRepository<Device, UUID> {

    Optional<Device> findById(UUID id);

    void deleteById(UUID id);

    List<Device> findByWarehouse(Warehouse warehouse);

}
