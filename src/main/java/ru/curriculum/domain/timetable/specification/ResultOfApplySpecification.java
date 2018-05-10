package ru.curriculum.domain.timetable.specification;

import java.util.ArrayList;
import java.util.List;

public class ResultOfApplySpecification implements IResultOfApplySpecification {
    private List<String> errors;

    private List<String> warnings;

    public ResultOfApplySpecification(List<String> errors, List<String> warnings) {
        this.errors = errors;
        this.warnings = warnings;
    }

    public ResultOfApplySpecification() {
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addErrors(List<String> errorMessages) {
        this.errors.addAll(errorMessages);
    }

    public void addError(String errorMessage) {
        this.errors.add(errorMessage);
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void addWarning(String warningMessage) {
        this.warnings.add(warningMessage);
    }

    public void addWarnings(List<String> warningMessages) {
        this.warnings.addAll(warningMessages);
    }

    public boolean isSuccess() {
        return 0 == errors.size() && 0 == warnings.size();
    }
}
