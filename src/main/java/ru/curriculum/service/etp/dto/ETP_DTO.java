package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.presentation.dto.EMASectionDTO;
import ru.curriculum.presentation.dto.OMASectionDTO;

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
    private List<EAModuleDTO> eaModules;
    private List<EMASectionDTO> emaSections;
    private List<OMASectionDTO> omaSections;

    public ETP_DTO() {
        this.eaModules = new ArrayList<>();
        this.emaSections = new ArrayList<>();
    }

    public ETP_DTO(ETP etp) {
        this.id = etp.id();
        this.title = etp.title();
        this.target = etp.target();
        this.distanceLearningBeginDate = etp.distanceLearningBeginDate();
        this.distanceLearningEndDate = etp.distanceLearningEndDate();
        this.fullTimeLearningBeginDate = etp.fullTimeLearningBeginDate();
        this.fullTimeLearningEndDate = etp.fullTimeLearningEndDate();
        this.eaModules =
                etp.eaModules()
                        .stream()
                        .map(EAModuleDTO::new)
                        .sorted(Comparator.comparing(EAModuleDTO::getId))
                        .collect(toList());
        this.emaSections =
                etp.emaSections()
                        .stream()
                        .map(EMASectionDTO::new)
                        .sorted(Comparator.comparing(EMASectionDTO::getId))
                        .collect(toList());
        this.omaSections =
                etp.omaSections()
                        .stream()
                        .map(OMASectionDTO::new)
                        .sorted(Comparator.comparing(OMASectionDTO::getId))
                        .collect(toList());
    }
}
