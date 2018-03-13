package ru.curriculum.service.etp.statusManager;

public class IllegalTransitionException extends RuntimeException {
    public IllegalTransitionException(ETPStatus previous, ETPStatus target) {
        super(String.format(
                "Невозможно перевести УТП из статуса \"%s\" в статус \"%s\" ",
                previous.getDisplayName(),
                target.getDisplayName())
        );
    }
}
