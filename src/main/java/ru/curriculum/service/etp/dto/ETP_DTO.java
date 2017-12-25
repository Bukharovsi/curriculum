package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.presentation.dto.EducationMethodicalSectionDTO;
import ru.curriculum.presentation.dto.OrganizationallyMethodicalSectionDTO;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Setter
@Getter
public class ETP_DTO {
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
    private List<EducationActivityModuleDTO> modules;
    private List<EducationMethodicalSectionDTO> edMetSec;
    private List<OrganizationallyMethodicalSectionDTO> orgMetSect;

    public ETP_DTO() {
        this.modules = new ArrayList<>();
        this.edMetSec = new ArrayList<>();
    }

    public ETP_DTO(ETP etp) {
        this.id = etp.id();
        this.title = etp.title();
        this.target = etp.target();
        this.distanceLearningBeginDate = etp.distanceLearningBeginDate();
        this.distanceLearningEndDate = etp.distanceLearningEndDate();
        this.fullTimeLearningBeginDate = etp.fullTimeLearningBeginDate();
        this.fullTimeLearningEndDate = etp.fullTimeLearningEndDate();
        this.modules =
                etp.educationActivityModules()
                        .stream()
                        .map(EducationActivityModuleDTO::new)
                        .sorted(Comparator.comparing(EducationActivityModuleDTO::getId))
                        .collect(toList());
        this.edMetSec =
                etp.educationMethodicalSections()
                        .stream()
                        .map(EducationMethodicalSectionDTO::new)
                        .sorted(Comparator.comparing(EducationMethodicalSectionDTO::getId))
                        .collect(toList());
        this.orgMetSect =
                etp.organizationallyMethodicalSections()
                        .stream()
                        .map(OrganizationallyMethodicalSectionDTO::new)
                        .sorted(Comparator.comparing(OrganizationallyMethodicalSectionDTO::getId))
                        .collect(toList());
    }
}
