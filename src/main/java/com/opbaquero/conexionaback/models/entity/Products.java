package com.opbaquero.conexionaback.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "name_product")
    private String nameProduct;

    @JoinColumn(name = "FK_ACCOUNT", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Account account;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    List<WareHouseProduct> wareHouse = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<WareHouseProduct> getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(List<WareHouseProduct> wareHouse) {
        this.wareHouse = wareHouse;
    }
}
