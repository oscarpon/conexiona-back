package com.opbaquero.conexionaback.models.service.impl;

import com.opbaquero.conexionaback.models.dao.IProductDao;
import com.opbaquero.conexionaback.models.dao.IReplacementDao;
import com.opbaquero.conexionaback.models.entity.*;
import com.opbaquero.conexionaback.models.service.dto.ReplacementDataExportDTO;
import com.opbaquero.conexionaback.models.service.interfaces.IReplacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReplacementServiceImpl implements IReplacementService {

    @Autowired
    private IReplacementDao replacementDao;

    @Autowired
    private IProductDao productDao;

    @Override
    @Transactional(readOnly = true)
    public List<Replacement> findAll() {
        return replacementDao.findAll();
    }

    @Override
    @Transactional
    public Replacement save(Replacement replacement) {
        return replacementDao.save(replacement);
    }

    @Override
    public Replacement findOne(UUID id) {
        return replacementDao.findById(id).orElse(null);
    }

    @Override
    public List<Replacement> findByWarehouse(Warehouse warehouse){
        return replacementDao.findByWarehouse(warehouse);
    }

    @Override
    @Transactional(readOnly = true)
    public Replacement fetchReplacementByIdWithUserWithReplacementItemWithProducts(UUID id) {
        return replacementDao.fetchByIdWithUserWithReplacementItemWithProducts(id);
    }

    @Override
    @Transactional
    public List<ReplacementDataExportDTO> findDataRepositionByAccount(UUID id){
        return replacementDao.findDataRepositionByAccount(id);
    }


}
