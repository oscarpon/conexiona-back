package com.opbaquero.conexionaback.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "device_reads")
public class Read {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private LocalDateTime dateRead;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_device", referencedColumnName = "id")
    @JsonIgnore
    private Device device;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_warehouseproduct", referencedColumnName = "id")
    @JsonIgnore
    private WareHouseProduct wareHouseProduct;

    public Read() {
    }

    public Read(Device device, WareHouseProduct wareHouseProduct) {
        this.device = device;
        this.wareHouseProduct = wareHouseProduct;
        this.dateRead = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDateRead() {
        return dateRead;
    }

    public void setDateRead(LocalDateTime dateRead) {
        this.dateRead = dateRead;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public WareHouseProduct getWareHouseProduct() {
        return wareHouseProduct;
    }

    public void setWareHouseProduct(WareHouseProduct wareHouseProduct) {
        this.wareHouseProduct = wareHouseProduct;
    }
}
