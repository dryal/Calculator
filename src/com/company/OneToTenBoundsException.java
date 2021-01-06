package com.company;

public class OneToTenBoundsException extends Exception{
    private String message;
    public OneToTenBoundsException(String message){
        super(message);
        this.message = message;
    }
}
