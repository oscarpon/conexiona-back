package com.opbaquero.conexionaback.utils;

import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.models.entity.Products;
import com.opbaquero.conexionaback.models.exceptions.NotAllowedException;
import com.opbaquero.conexionaback.models.service.interfaces.IProductService;
import com.opbaquero.conexionaback.security.entity.User;
import com.opbaquero.conexionaback.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AuthorizationUtils {

    @Autowired
    public IProductService productService;

    @Autowired
    public UserService userService;

    public boolean checkAccountsProducts(UUID productId){
        User usuario = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        if(usuario.getRoles().size() == 3){
            return true;
        }else{
            List<Products> productos = productService.findByAccount(usuario.getAccount());
            for(Products producto : productos){
                if(producto.getId().equals(productId)){
                    return true;
                }
            }
            throw new NotAllowedException("El producto no pertenece a tu cuenta");
        }
    }

}
