package ru.curriculum.domain.etp.entity.financingSource;

public enum  FinancingSource {
    BUDGET("Бюджет"),
    NOT_BUDGET("Вне Бюджет");


    private final String displayName;

    FinancingSource(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
