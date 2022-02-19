package com.opbaquero.conexionaback.models.service.dto;

import java.util.UUID;

public class AsociateDeviceProductDTO {

    private UUID wareHouseProductId;

    private UUID deviceId;

    public AsociateDeviceProductDTO(UUID wareHouseProductId, UUID deviceId) {
        this.wareHouseProductId = wareHouseProductId;
        this.deviceId = deviceId;
    }

    public UUID getWareHouseProductId() {
        return wareHouseProductId;
    }

    public void setWareHouseProductId(UUID wareHouseProductId) {
        this.wareHouseProductId = wareHouseProductId;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }
}
