package com.company;

public class ZeroToTenBoundsException extends Exception{
    private String message;
    public ZeroToTenBoundsException(String message){
        super(message);
        this.message = message;
    }
}
