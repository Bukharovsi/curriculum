package ru.curriculum.service.timetable.dto;

import lombok.Getter;
import ru.curriculum.domain.timetable.specification.ResultOfApplySpecification;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TimetableDtoValidation {
    private List<String> errors;
    private List<String> warnings;

    public TimetableDtoValidation() {
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    public TimetableDtoValidation(ResultOfApplySpecification resultOfApplySpecification) {
        this.errors = resultOfApplySpecification.getErrors();
        this.warnings = resultOfApplySpecification.getWarnings();
    }

    public boolean isSuccess() {
        return !(hasErrors() || hasWarnings());
    }

    public boolean hasErrors() {
        return 0 != errors.size();
    }

    public boolean hasWarnings() {
        return  0 != warnings.size();
    }
}
