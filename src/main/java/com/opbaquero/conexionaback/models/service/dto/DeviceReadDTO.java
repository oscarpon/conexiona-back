package com.opbaquero.conexionaback.models.service.dto;

import com.opbaquero.conexionaback.models.entity.Device;

import java.time.LocalDateTime;
import java.util.UUID;

public class DeviceReadDTO {

    public UUID id;

    public LocalDateTime date_read;

    public String nameProduct;

    public Device idDevice;


    public DeviceReadDTO(UUID id, String nameProduct, Device idDevice) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.idDevice = idDevice;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDate_read() {
        return date_read;
    }

    public void setDate_read(LocalDateTime date_read) {
        this.date_read = date_read;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Device getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(Device idDevice) {
        this.idDevice = idDevice;
    }
}
