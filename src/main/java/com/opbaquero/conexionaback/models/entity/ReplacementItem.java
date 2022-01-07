package com.opbaquero.conexionaback.models.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "replacement_item")
public class ReplacementItem implements Serializable {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private int quantityReplaced;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private WareHouseProduct product;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getQuantityReplaced() {
        return quantityReplaced;
    }

    public void setQuantityReplaced(int quantityReplaced) {
        this.quantityReplaced = quantityReplaced;
    }

    public WareHouseProduct getProduct() {
        return product;
    }

    public void setProduct(WareHouseProduct product) {
        this.product = product;
    }
}
