package com.opbaquero.conexionaback.models.entity;

import com.opbaquero.conexionaback.security.entity.User;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "replacements")
public class Replacement implements Serializable {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "replacement_id")
    private List<ReplacementItem> replacementItems;

    public Replacement() {
        this.replacementItems = new ArrayList<ReplacementItem>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public List<ReplacementItem> getReplacementItems() {
        return replacementItems;
    }

    public void setReplacementItems(List<ReplacementItem> replacementItems) {
        this.replacementItems = replacementItems;
    }

    public void addItemReplacement(ReplacementItem item){
        this.replacementItems.add(item);
    }
}
