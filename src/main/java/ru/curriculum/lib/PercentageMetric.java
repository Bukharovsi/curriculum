package ru.curriculum.lib;

import lombok.Getter;

@Getter
public class PercentageMetric {
    private int percentage;

    public PercentageMetric(int percentage) {
        if(0 > percentage || 100 < percentage) {
            throw new IllegalArgumentException("Allowable value from 0 to 100");
        }
        this.percentage = percentage;
    }
}
