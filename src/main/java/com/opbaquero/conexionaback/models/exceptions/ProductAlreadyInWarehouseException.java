package com.opbaquero.conexionaback.models.exceptions;

public class ProductAlreadyInWarehouseException extends Exception{

    public ProductAlreadyInWarehouseException(String message){
        super(message);
    }

}
