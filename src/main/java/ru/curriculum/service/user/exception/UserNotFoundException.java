package ru.curriculum.service.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer userId) {
        super(String.format("Пользователь с идентификатором %s не найден в системе", userId));
    }
}
