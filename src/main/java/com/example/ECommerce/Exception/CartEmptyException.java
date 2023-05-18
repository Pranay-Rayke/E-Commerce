package com.example.ECommerce.Exception;

public class CartEmptyException extends Exception{

    public CartEmptyException(String message)
    {
        super(message);
    }
}
