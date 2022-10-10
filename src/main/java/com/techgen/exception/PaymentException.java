package com.techgen.exception;

public class PaymentException extends RuntimeException {

    private String message;

    public PaymentException(String message) {
        super(message);
        this.message = message;
    }
}
