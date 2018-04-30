package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TotalRow {
    private Double lectures = 0.0;
    private Double practices = 0.0;
    private Double independentWorks = 0.0;
    private Double consultations = 0.0;
    private Double peerReviews = 0.0;
    private Double credits = 0.0;
    private Double others = 0.0;
    private Double standard = 0.0;
    private Double hoursPerOneListener = 0.0;
    private Double totalHours = 0.0;
    private Integer lernerCount = 0;
    private Integer groupCount = 0;
    private Integer conditionalPagesCount = 0;
}
