package com.opbaquero.conexionaback.models.service.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ReplacementDTO {

    private UUID id;
    private Date date;
    private String user;
    private String wareHouse;
    private List<ReplacementItemDTO> replacementItems;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(String wareHouse) {
        this.wareHouse = wareHouse;
    }

    public List<ReplacementItemDTO> getReplacementItems() {
        return replacementItems;
    }

    public void setReplacementItems(List<ReplacementItemDTO> replacementItems) {
        this.replacementItems = replacementItems;
    }
}
