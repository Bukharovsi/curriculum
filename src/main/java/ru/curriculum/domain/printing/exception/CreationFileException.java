package ru.curriculum.domain.printing.exception;


public class CreationFileException extends RuntimeException {
    public CreationFileException(Exception e) {
        super(String.format("Can't create excel file because %s", e.getMessage()));
    }
}
