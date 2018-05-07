package ru.curriculum.domain.timetable.specification;

import java.util.ArrayList;
import java.util.List;

public class ResultOfApplySpecification implements IResultOfApplySpecification {
    private List<String> errorMessages;

    public ResultOfApplySpecification() {
        this.errorMessages = new ArrayList<>();
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void addErrors(List<String> errorMessages) {
        this.errorMessages.addAll(errorMessages);
    }

    public void addError(String errorMessage) {
        this.errorMessages.add(errorMessage);
    }

    public boolean isSuccess() {
        return 0 == errorMessages.size();
    }
}
