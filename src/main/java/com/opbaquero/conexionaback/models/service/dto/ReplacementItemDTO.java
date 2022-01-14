package com.opbaquero.conexionaback.models.service.dto;

import java.util.UUID;

public class ReplacementItemDTO {

    private String id;

    private Integer quantity;

    private UUID product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public UUID getProduct() {
        return product;
    }

    public void setProduct(UUID product) {
        this.product = product;
    }
}
