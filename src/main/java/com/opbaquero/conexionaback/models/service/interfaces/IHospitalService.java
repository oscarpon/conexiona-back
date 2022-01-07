package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.models.entity.Hospital;

import java.util.List;
import java.util.UUID;

public interface IHospitalService {

    public List<Hospital> findAll();

    public Hospital save(Hospital hospital);

    public Hospital findOne(UUID id);

    public void delete(UUID id);

    public List<Hospital> findByAccount(Account account);

}
