package ru.curriculum.domain.admin.curator.exceptions;

public class IllegalPasswordException extends RuntimeException {
    public IllegalPasswordException() {
        super("Password length must be grater than 3");
    }
}
