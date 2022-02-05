package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.entity.*;
import com.opbaquero.conexionaback.models.service.dto.ReplacementDataExportDTO;

import java.util.List;
import java.util.UUID;

public interface IReplacementService {

    public List<Replacement> findAll();

    public Replacement save(Replacement replacement);

    public Replacement findOne(UUID id);

    public List<Replacement> findByWarehouse(Warehouse warehouse);

    public  Replacement fetchReplacementByIdWithUserWithReplacementItemWithProducts(UUID id);

    public List<ReplacementDataExportDTO> findDataRepositionByAccount(UUID id);
}
