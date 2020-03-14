package com.amos.amos.global.accelerex.Usecase.api.exception;

public class InValidRequest extends RuntimeException {

    public InValidRequest(String message) {
        throw new RuntimeException(message);
    }
}
