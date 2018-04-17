package ru.curriculum.domain.printing.exception;

public class CreationTemplateException extends RuntimeException {
    public CreationTemplateException(Exception e) {
        super(e);
    }
}
