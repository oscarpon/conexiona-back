package com.opbaquero.conexionaback.models.exceptions;

public class ProductAlreadyInWarehouseException extends RuntimeException{

    public ProductAlreadyInWarehouseException(String message){
        super(message);
    }

}
