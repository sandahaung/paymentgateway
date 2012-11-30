package com.paymentgateway.paymentgateway.utils;

public class NameValidation {
    private boolean exists;
    private String message;

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
