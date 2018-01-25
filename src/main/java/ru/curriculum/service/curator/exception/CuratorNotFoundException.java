package ru.curriculum.service.curator.exception;

public class CuratorNotFoundException extends RuntimeException {
    public CuratorNotFoundException(Integer curatorId) {
        super(String.format("Куратор с идентификатором %s не найден в системе", curatorId));
    }
}
