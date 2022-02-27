package com.opbaquero.conexionaback.models.service.dto;

import java.util.UUID;

public class WarehouseProductDTO {

    public UUID product;
    public UUID warehouse;
    public Integer stock;
    public String deviceName;
    public String idBasket;

    public WarehouseProductDTO() {
    }

    public UUID getProduct() {
        return product;
    }

    public void setProduct(UUID product) {
        this.product = product;
    }

    public UUID getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(UUID warehouse) {
        this.warehouse = warehouse;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getIdBasket() {
        return idBasket;
    }

    public void setIdBasket(String idBasket) {
        this.idBasket = idBasket;
    }
}
