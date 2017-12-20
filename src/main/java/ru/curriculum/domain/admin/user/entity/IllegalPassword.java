package ru.curriculum.domain.admin.user.entity;

public class IllegalPassword extends RuntimeException {
    public IllegalPassword() {
        super("Password length must be grater than 3");
    }
}
