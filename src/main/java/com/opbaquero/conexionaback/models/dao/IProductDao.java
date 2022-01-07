package com.opbaquero.conexionaback.models.dao;

import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.models.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProductDao extends JpaRepository<Products, UUID> {
    List<Products> findByAccount(Account account);

    Optional<Products> findById(UUID id);

    public List<Products> findByNameProduct(String nameProduct);

    public List<Products> findByNameProductLikeIgnoreCase(String nameProduct);

    void deleteById(UUID id);
}
