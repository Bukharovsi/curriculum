package ru.curriculum.service.etp.statusManager;

public class IllegalTransitionException extends RuntimeException {
    public IllegalTransitionException(ETPStatus previous, ETPStatus target) {
        super(String.format("Can't move ETP from %s to %s", previous, target));
    }
}
