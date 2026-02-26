package com.todo.onlineexaminationsystem.exception;

public class BadRequestException extends  ApiException {
    public BadRequestException(String message) {
        super(message);
    }
}
