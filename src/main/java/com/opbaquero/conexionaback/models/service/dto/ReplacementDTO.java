package com.opbaquero.conexionaback.models.service.dto;

import java.util.List;
import java.util.UUID;

public class ReplacementDTO {

    private String user;
    private UUID warehouse;
    private List<ReplacementItemDTO> replacementItems;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public UUID getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(UUID warehouse) {
        this.warehouse = warehouse;
    }

    public List<ReplacementItemDTO> getReplacementItems() {
        return replacementItems;
    }

    public void setReplacementItems(List<ReplacementItemDTO> replacementItems) {
        this.replacementItems = replacementItems;
    }
}
