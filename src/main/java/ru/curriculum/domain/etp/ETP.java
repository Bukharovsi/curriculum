package ru.curriculum.domain.etp;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/*
 * ETP - education thematic plan (УПТ - учебно-тематический план)
 */
@Getter
public class ETP {
    private Integer id;
    private String title;
    private String target;
    private String distanceLearningBeginDate;
    private String distanceLearningEndDate;
    private String fullTimeBeginDate;
    private String fullTimeEndDate;
//    private EducationMethodicalActivity educationMethodicalActivity;
}
