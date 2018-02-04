package ru.curriculum.service.etp.exception;

public class ETPAlreadyCreatedFromStateProgramException extends RuntimeException {
    public ETPAlreadyCreatedFromStateProgramException(Integer stateProgramId) {
        super(String.format(
                "На основании учебного плана с идентификатором %s, уже было сформировано УТП",
                stateProgramId
        ));
    }
}
