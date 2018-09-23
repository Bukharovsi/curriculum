package ru.curriculum.domain.printing.file.template.specific;

import lombok.Getter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.printing.file.excel.CellAddress;


@Getter()
@Accessors(fluent = true)
public class ETPTemplateCoordinates {
    private final Integer tableStartRow = 45;
    private final Integer topicName = 1;
    private final Integer lectures = 2;
    private final Integer practices = 3;
    private final Integer independentWorks = 4;
    private final Integer consultations = 5;
    private final Integer peerReviews = 6;
    private final Integer credits = 7;
    private final Integer others = 8;
    private final Integer hoursPerOneListener = 9;
    private final Integer standard = 10;
    private final Integer lernerCount = 11;
    private final Integer groupCount = 12;
    private final Integer conditionalPagesCount = 13;
    private final Integer totalHours = 14;
    private final Integer teacher = 15;

    private final CellAddress vihTotal;
    private final CellAddress vihPerOneListener;
    private final CellAddress vihFullTimePerOneListener;
    private final CellAddress vihDistancePerOneListener;
    private final CellAddress vihTotalStudyWork;
    private final CellAddress vihFullTimeStudyWork;
    private final CellAddress vihDistanceStudyWork;
    private final CellAddress vihPaymentStudyWork;
    private final CellAddress vihFullTimePaymentForStudyWork;
    private final CellAddress vihDistancePaymentForStudyWork;
    private final CellAddress vihEmaPaymentForStudyWork;
    private final CellAddress vihOmaPaymentForStudyWork;
    private final CellAddress vihInLoad;
    private final CellAddress vihFullTimeInLoad;
    private final CellAddress vihDistanceInLoad;
    private final CellAddress vihEmaInLoad;
    private final CellAddress vihOmaInLoad;

    /**
     * Offset after "Education Activity module" in excel file template
     * for that we can add pivot row
     */
    private final Integer offset = 15;

    private CellAddress etpTheme;
    private CellAddress etpTarget;
    private CellAddress etpBeginDate;
    private CellAddress etpEndDate;
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
        this.etpBeginDate = new CellAddress(19,2);
        this.etpEndDate = new CellAddress(19,8);
        this.etpDistanceBeginDate = new CellAddress(20,2);
        this.etpDistanceEndDate = new CellAddress(20,8);
        this.etpFullTimeBeginDate = new CellAddress(21,2);
        this.etpFullTimeEndDate = new CellAddress(21,8);
        this.etpSchoolDays = new CellAddress(22, 2);
        this.etpLernerCount = new CellAddress(23, 2);
        this.etpFinancingSource = new CellAddress(24, 2);

        this.vihTotal = new CellAddress(25, 2);
        this.vihPerOneListener = new CellAddress(26, 2);
        this.vihDistancePerOneListener = new CellAddress(27, 2);
        this.vihFullTimePerOneListener = new CellAddress(28, 2);
        this.vihTotalStudyWork = new CellAddress(29, 2);
        this.vihDistanceStudyWork = new CellAddress(30, 2);
        this.vihFullTimeStudyWork = new CellAddress(31, 2);
        this.vihPaymentStudyWork = new CellAddress(32, 2);
        this.vihDistancePaymentForStudyWork = new CellAddress(33, 2);
        this.vihFullTimePaymentForStudyWork = new CellAddress(34, 2);
        this.vihEmaPaymentForStudyWork = new CellAddress(35, 2);
        this.vihOmaPaymentForStudyWork = new CellAddress(36, 2);
        this.vihInLoad = new CellAddress(37, 2);
        this.vihDistanceInLoad = new CellAddress(38, 2);
        this.vihFullTimeInLoad = new CellAddress(39, 2);
        this.vihEmaInLoad = new CellAddress(40, 2);
        this.vihOmaInLoad = new CellAddress(41, 2);
    }
}
