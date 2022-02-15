package com.opbaquero.conexionaback.models.service.dto;

public class ActualStockDTO {

    private String wareHouse;

    private String product;

    private Integer stock;

    public ActualStockDTO(String wareHouse, String product, Integer stock) {
        this.wareHouse = wareHouse;
        this.product = product;
        this.stock = stock;
    }

    public String getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(String wareHouse) {
        this.wareHouse = wareHouse;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
