package com.opbaquero.conexionaback.models.exceptions;

public class NotAllowedException extends RuntimeException{

    public NotAllowedException(String message){
        super(message);
    }

}
