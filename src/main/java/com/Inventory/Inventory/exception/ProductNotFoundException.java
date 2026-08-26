package com.Inventory.Inventory.exception;



public class ProductNotFoundException
        extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
