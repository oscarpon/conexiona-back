package com.opbaquero.conexionaback.models.service.dto;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.UUID;

public class ReplacementDataExportDTO {

    public UUID id;

    public Date date;

    public String userName;

    public String wareHouseName;

    public String buildingName;

    public String hospitalName;

    public String accountName;

    public ReplacementDataExportDTO(UUID id, Date date, String userName, String wareHouseName, String buildingName, String hospitalName, String accountName) {
        this.id = id;
        this.date = date;
        this.userName = userName;
        this.wareHouseName = wareHouseName;
        this.buildingName = buildingName;
        this.hospitalName = hospitalName;
        this.accountName = accountName;
    }

    public Date getDate() {
        return date;
    }

    public String getUserName() {
        return userName;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getAccountName() {
        return accountName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
