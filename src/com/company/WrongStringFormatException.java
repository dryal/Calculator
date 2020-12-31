package com.company;

public class WrongStringFormatException extends Exception{
    private String message;
    public WrongStringFormatException(String message)
        {
            super(message);
            this.message = message;
        };
    }

