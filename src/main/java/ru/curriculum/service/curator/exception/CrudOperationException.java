package ru.curriculum.service.curator.exception;

public class CrudOperationException extends RuntimeException {
    public CrudOperationException(String message) {
        super(message);
    }
}
