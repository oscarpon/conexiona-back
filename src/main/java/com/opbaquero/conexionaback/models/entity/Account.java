package com.opbaquero.conexionaback.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opbaquero.conexionaback.security.entity.User;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(nullable = false, name = "account_name")
    private String accountName;

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JsonIgnore
    private List<Hospital> hospitals = new ArrayList<>();

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JsonIgnore
    private List<User> users = new ArrayList<>();

   @OneToMany(mappedBy = "account", orphanRemoval = true)
   @Cascade(org.hibernate.annotations.CascadeType.DELETE)
   @JsonIgnore
   private List<Products> products = new ArrayList<>();

    public UUID getId() {
        String idAccountAdmin = "9feeecc9-c746-408e-872f-ed2320e6b7dd";
        UUID idAdmin = UUID.fromString(idAccountAdmin);
        if(id != null){
            return id;
        }else{
            return idAdmin;
        }

    }

    public void setId(UUID idAccount) {
        this.id = idAccount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public List<User> getUsers(){
        return users;
    }

    public List<Hospital> getHospitals(){ return hospitals;}

}
