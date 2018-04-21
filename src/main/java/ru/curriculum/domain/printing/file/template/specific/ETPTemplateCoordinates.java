package ru.curriculum.domain.printing.file.template.specific;

import lombok.Getter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.printing.file.excel.CellAddress;


@Getter()
@Accessors(fluent = true)
public class ETPTemplateCoordinates {
    private final Integer tableStartRow = 29;
    private final Integer topicName = 1;
    private final Integer lectures = 2;
    private final Integer practices = 3;
    private final Integer independentWorks = 4;
    private final Integer consultations = 5;
    private final Integer peerReviews = 6;
    private final Integer credits = 7;
    private final Integer others = 8;
    private final Integer standard = 10;
    private final Integer totalHours = 11;
    private final Integer lernerCount = 12;
    private final Integer groupCount = 13;
    private final Integer conditionalPagesCount = 14;
    private final Integer teacher = 15;

    /**
     * Offset after "Education Activity module" in excel file template
     * for that we can add pivot row
     */
    private final Integer offset = 15;

    private CellAddress etpTheme;
    private CellAddress etpTarget;
    private CellAddress etpDistanceBeginDate;
    private CellAddress etpDistanceEndDate;
    private CellAddress etpFullTimeBeginDate;
    private CellAddress etpFullTimeEndDate;
    private CellAddress etpLernerCount;
    private CellAddress etpSchoolDays;
    private CellAddress etpFinancingSource;

    public ETPTemplateCoordinates() {
        this.etpTheme = new CellAddress(14, 0);
        this.etpTarget = new CellAddress(17, 0);
        this.etpDistanceBeginDate = new CellAddress(20,2);
        this.etpDistanceEndDate = new CellAddress(20,8);
        this.etpFullTimeBeginDate = new CellAddress(21,2);
        this.etpFullTimeEndDate = new CellAddress(21,8);
        this.etpSchoolDays = new CellAddress(22, 2);
        this.etpLernerCount = new CellAddress(23, 2);
        this.etpFinancingSource = new CellAddress(24, 2);

    }
}
