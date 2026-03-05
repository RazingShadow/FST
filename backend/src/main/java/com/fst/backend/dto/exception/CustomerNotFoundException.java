package com.fst.backend.dto.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Customer with id " + id + " not found.");
    }
}
