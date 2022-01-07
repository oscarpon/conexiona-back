package com.opbaquero.conexionaback.models.service.impl;

import com.opbaquero.conexionaback.models.dao.IHospitalDao;
import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.models.entity.Hospital;
import com.opbaquero.conexionaback.models.service.interfaces.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class HospitalServiceImpl implements IHospitalService {

    @Autowired
    private IHospitalDao hospitalDao;

    @Override
    @Transactional(readOnly = true)
    public List<Hospital> findAll() {
        return (List<Hospital>) hospitalDao.findAll();
    }

    @Override
    @Transactional
    public Hospital save(Hospital hospital) {
        return hospitalDao.save(hospital);
    }

    @Override
    public Hospital findOne(UUID id) {
        return hospitalDao.findById(id).orElse(null);
    }

    @Override
    public void delete(UUID id) {
        hospitalDao.deleteById(id);
    }

    @Transactional
    public List<Hospital> findByAccount(Account account){return hospitalDao.findByAccount(account); }
}
