package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.entity.*;
import com.opbaquero.conexionaback.models.service.dto.ReplacementDataExportDTO;
import com.opbaquero.conexionaback.models.service.dto.ReplacementItemDTO;

import java.util.List;
import java.util.UUID;

public interface IReplacementService {

    List<Replacement> findAll();

    Replacement save(Replacement replacement);

    Replacement findOne(UUID id);

    List<Replacement> findByWarehouse(Warehouse warehouse);

    Replacement fetchReplacementByIdWithUserWithReplacementItemWithProducts(UUID id);

    List<ReplacementDataExportDTO> findDataRepositionByAccount(UUID id);

    List<ReplacementItemDTO> findItemsByRepositionId(UUID id);


}
