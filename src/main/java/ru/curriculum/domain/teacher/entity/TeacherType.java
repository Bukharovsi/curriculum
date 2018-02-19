package ru.curriculum.domain.teacher.entity;

public enum TeacherType {
    INVITED("Приглашенный"),
    STAFF("Штатный");

    private String displayName;

    TeacherType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
