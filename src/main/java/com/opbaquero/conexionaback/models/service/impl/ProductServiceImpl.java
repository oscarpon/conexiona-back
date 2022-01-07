package com.opbaquero.conexionaback.models.service.impl;

import com.opbaquero.conexionaback.models.dao.IProductDao;
import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.models.entity.Products;
import com.opbaquero.conexionaback.models.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    @Transactional(readOnly = true)
    public List<Products> findAll() {
        return productDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Products> findByNameProduct(String nameProduct) {
        return productDao.findByNameProduct(nameProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public Products findById(UUID id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    public Products save(Products products) {
        return productDao.save(products);
    }

    @Override
    public Products findOne(UUID id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    public void delete(UUID id) {
        productDao.deleteById(id);
    }

    @Override
    public List<Products> findByAccount(Account account) {
        return productDao.findByAccount(account);
    }
}
