package ru.curriculum.service.stateSchedule.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import ru.curriculum.domain.stateSchedule.entity.Internship;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class InternshipDto {

    private Integer id;

    @NotNull(message = "Необходимо заполнить \"Дата начала стажировки\"")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;

    @NotNull(message = "Необходимо заполнить \"Дата окончания стажировки\"")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFinish;

    private String address;

    @NotEmpty(message = "Необходимо заполнить \"Тема стажировки\"")
    private String theme;

    public InternshipDto(Internship internship) {
        this.id = internship.id();
        this.dateStart = internship.dateStart();
        this.dateFinish = internship.dateFinish();
        this.address = internship.address();
        this.theme = internship.theme();
    }
}
