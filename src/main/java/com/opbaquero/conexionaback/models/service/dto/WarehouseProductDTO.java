package com.opbaquero.conexionaback.models.service.dto;

import java.util.UUID;

public class WarehouseProductDTO {

    public UUID product;
    public UUID warehouse;
    public Integer stock;

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
}
