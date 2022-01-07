package com.opbaquero.conexionaback.models.service.interfaces;

import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.models.entity.Products;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    public List<Products> findAll();

    public List<Products> findByNameProduct(String nameProduct);

    public Products findById(UUID id);

    public Products save(Products products);

    public Products findOne(UUID id);

    public void delete(UUID id);

    public List<Products> findByAccount(Account account);
}
