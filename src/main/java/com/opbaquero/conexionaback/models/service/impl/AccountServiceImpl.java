package com.opbaquero.conexionaback.models.service.impl;

import com.opbaquero.conexionaback.models.dao.IAccountDao;
import com.opbaquero.conexionaback.models.dao.IHospitalDao;
import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.models.entity.Hospital;
import com.opbaquero.conexionaback.models.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao cuentaDao;

    @Autowired
    private IHospitalDao hospitalDao;

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return (List<Account>) cuentaDao.findAll();
    }

    @Override
    @Transactional
    public Account save(Account account) {
        return  cuentaDao.save(account);
    }

    @Override
    public Account findOne(UUID id) {
        return cuentaDao.findById(id).orElse(null);
    }

    @Override
    public void delete(UUID id) {
        cuentaDao.deleteById(id);
    }

    @Override
    public List<Hospital> findHospitalById(UUID id) {
        return (List<Hospital>) hospitalDao.findById(id).orElse(null);
    }

    @Override
    public Hospital saveHospital(Hospital hospital) {
       return  hospitalDao.save(hospital);
    }

    @Override
    public boolean existsByAccountName(String accountName){
        return cuentaDao.existsByAccountName(accountName);
    }

    @Override
    public UUID findByAccountName(String accountName){
        return cuentaDao.findByAccountName(accountName);
    }

}
