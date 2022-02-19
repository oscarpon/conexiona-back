package com.opbaquero.conexionaback.models.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "devices")
public class Device implements Serializable {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "id_basket")
    private String idBasket;

    @Column(name = "device_name")
    private String deviceName;

    @OneToOne(mappedBy = "device")
    private WareHouseProduct wareHouseProduct;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdBasket() {
        return idBasket;
    }

    public void setIdBasket(String idBasket) {
        this.idBasket = idBasket;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
