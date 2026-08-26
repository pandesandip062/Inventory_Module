package com.Inventory.Inventory.exception;



public class ProductAlreadyExistsException
        extends RuntimeException {

    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
