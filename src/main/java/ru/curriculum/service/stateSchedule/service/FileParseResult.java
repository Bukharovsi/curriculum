package ru.curriculum.service.stateSchedule.service;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.stateSchedule.stateProgramFileParser.StateProgramTemplate;

import java.util.ArrayList;
import java.util.List;

public class FileParseResult {
    @Getter
    private List<String> errors;

    @Setter
    @Getter
    private List<StateProgramTemplate> stateProgramTemplates;

    public FileParseResult() {
        errors = new ArrayList<>();
    }

    public boolean parseIsSuccess() {
        return 0 == errors.size();
    }

    public void addError(String errorMessage) {
        errors.add(errorMessage);
    }
}
