package ru.curriculum.service.etp.statusManager;

import lombok.Getter;

public enum  ETPStatus {
    DRAFT("Проект УТП (Черновик)"),
    CONSIDERATION("Проект УТП для рассмотрения"),
    REVISION("Проект УТП для дораобтки"),
    STATEMENT("Проект УТП для утверждения");

    @Getter
    private final String displayName;

    ETPStatus(String displayName) {
        this.displayName = displayName;
    }
}
