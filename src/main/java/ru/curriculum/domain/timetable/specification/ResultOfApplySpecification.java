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

    public ResultOfApplySpecification merge(ResultOfApplySpecification anotherResult) {
        List<String> allErrors = anotherResult.getErrors();
        List<String> allWarnings = anotherResult.getWarnings();
        allErrors.addAll(this.getErrors());
        allWarnings.addAll(this.getWarnings());
        return new ResultOfApplySpecification(allErrors, allWarnings);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void addWarning(String warning) {
        this.warnings.add(warning);
    }

    public boolean isSuccess() {
        return 0 == errors.size() && 0 == warnings.size();
    }
}
