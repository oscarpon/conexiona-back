package com.opbaquero.conexionaback.models.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "warehouse_products")
public class WareHouseProduct implements Serializable {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @JoinColumn(name = "warehouse_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Warehouse warehouse;

    @JoinColumn(name = "product_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Products product;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private Device device;

    private int stock;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

   public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Products getProducts() {
        return product;
    }

    public void setProducts(Products products) {
        this.product = products;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
