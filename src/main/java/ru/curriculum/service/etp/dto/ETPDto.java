package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import ru.curriculum.domain.etp.entity.Comment;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.financingSource.FinancingSource;
import ru.curriculum.service.etp.statusManager.ETPStatus;
import ru.curriculum.service.etp.validation.EndDateLargerThanBeginDateConstraint;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Setter
@Getter
@EndDateLargerThanBeginDateConstraint.List({
        @EndDateLargerThanBeginDateConstraint(
                beginDate = "distanceLearningBeginDate", endDate = "distanceLearningEndDate",
                message = "Дата окончания дистанционного обучения должна быть больше даты начала"
        ),
        @EndDateLargerThanBeginDateConstraint(
                beginDate = "fullTimeLearningBeginDate", endDate = "fullTimeLearningEndDate",
                message = "Дата окончания очного обучения должна быть больше даты начала"
        )
})
public class ETPDto {

    private Integer id;

    @NotEmpty(message = "Необходими заполнить поле \"Название\"")
    private String title;

    @NotEmpty(message = "Необходими заполнить поле \"Цель\"")
    private String target;

    @NotNull(message = "Необходими заполнить поле \"Дата начала дистанционного обучения\"")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date distanceLearningBeginDate;

    @NotNull(message = "Необходими заполнить поле \"Дата окончания дистанционного обучения\"")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date distanceLearningEndDate;

    @NotNull(message = "Необходими заполнить поле \"Дата начала очного обучения\"")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fullTimeLearningBeginDate;

    @NotNull(message = "Необходими заполнить поле \"Дата окончания очного обучения\"")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fullTimeLearningEndDate;

    @NotNull(message = "Необходимо выбрать \"Источник финансирования\"")
    private FinancingSource financingSource;

    private Integer lernerCount;

    private Integer schoolDaysCount;

    private Integer stateProgramId;

    private ETPStatus actualStatus;

    private ETPStatus newStatus;

    private Integer timetableId;

    private VolumeInHoursDto volumeInHours;

    @Valid
    private List<EAModuleDto> eaModules;

    @Valid
    private List<EMAModuleDto> emaModules;

    @Valid
    private List<OMAModuleDto> omaModules;

    private List<CommentDto> comments;

    private TotalRow emaModuleTotalRow;

    private TotalRow eaModuleTotalRow;

    private TotalRow omaModuleTotalRow;

    private TotalRow etpTotalRow;

    public ETPDto() {
        this.actualStatus = ETPStatus.DRAFT;
        this.newStatus = ETPStatus.DRAFT;
        this.eaModules = new ArrayList<>();
        this.emaModules = new ArrayList<>();
        this.omaModules = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.volumeInHours = new VolumeInHoursDto();
        this.emaModuleTotalRow = new TotalRow();
        this.omaModuleTotalRow = new TotalRow();
        this.eaModuleTotalRow = new TotalRow();
        this.etpTotalRow = new TotalRow();
    }

    public ETPDto(ETP etp) {
        this();
        this.id = etp.id();
        this.title = etp.title();
        this.target = etp.target();
        this.distanceLearningBeginDate = etp.distanceLearningBeginDate();
        this.distanceLearningEndDate = etp.distanceLearningEndDate();
        this.fullTimeLearningBeginDate = etp.fullTimeLearningBeginDate();
        this.fullTimeLearningEndDate = etp.fullTimeLearningEndDate();
        this.stateProgramId = etp.stateProgramId();
        this.lernerCount = etp.lernerCount();
        this.schoolDaysCount = etp.schoolDaysCount();
        this.actualStatus = etp.status();
        this.newStatus = etp.status();
        this.financingSource = etp.financingSource();
        this.timetableId = null != etp.timetable() ? etp.timetable().id() : null;
        this.volumeInHours = null != etp.volumeInHours() ? new VolumeInHoursDto(etp.volumeInHours()) : new VolumeInHoursDto();
        this.eaModules =
                etp.eaModules()
                        .stream()
                        .map(EAModuleDto::new)
                        .sorted(Comparator.comparing(EAModuleDto::getId).reversed())
                        .collect(toList());
        this.emaModules =
                etp.emaModules()
                        .stream()
                        .map(EMAModuleDto::new)
                        .sorted(Comparator.comparing(EMAModuleDto::getId).reversed())
                        .collect(toList());
        this.omaModules =
                etp.omaModules()
                        .stream()
                        .map(OMAModuleDto::new)
                        .sorted(Comparator.comparing(OMAModuleDto::getId).reversed())
                        .collect(toList());
        this.comments = new ArrayList<>();
    }

    public ETPDto(ETP ept, List<Comment> comments) {
        this(ept);
        this.comments = comments.stream().map(CommentDto::new).collect(toList());
    }
}
