package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.models.entity.Hospital;

import java.util.List;
import java.util.UUID;

public interface IAccountService {

    public List<Account> findAll();

    public Account save (Account account);

    public Account findOne(UUID id);

    public void delete(UUID id);

    public List<Hospital> findHospitalById(UUID id);

    public Hospital saveHospital(Hospital hospital);

    public boolean existsByAccountName(String accountName);

    public UUID findByAccountName(String accountName);

}
