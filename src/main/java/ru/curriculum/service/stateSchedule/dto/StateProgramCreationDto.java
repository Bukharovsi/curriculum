package ru.curriculum.service.stateSchedule.dto;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Государственная программа, на основании которой формируется УТП
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StateProgramCreationDto {

    private Integer id;

    @NotEmpty(message = "Необходимо заполнить \"Целевая аудитория\"")
    private String targetAudience;

    @NotEmpty(message = "Необходимо заполнить \"Название\"")
    private String name;

    @NotEmpty(message = "Необходимо заполнить \"Форма обучения\"")
    private String modeId;

    @NotNull(message = "Необходимо заполнить \"Форма реализации\"")
    private String implementationFormId;

    @NotNull(message = "Необходимо заполнить \"Кол-во слушателей\"")
    private Integer lernerCount;

    @NotNull(message = "Необходимо заполнить \"Кол-во групп\"")
    private Integer groupCount;

    @NotNull(message = "Необходимо заполнить \"Объем на одного слушателя в часах\"")
    private Integer countOfHoursPerLerner;

    @NotNull(message = "Необходимо заполнить \"Дата начала\"")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;

    @NotNull(message = "Необходимо заполнить \"Дата окончания\"")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFinish;

    @NotNull(message = "Необходимо заполнить \"Ответсвенное подразделение\"")
    @Min(value = 1, message = "Необходимо заполнить \"Ответсвенное подразделение\"")
    private int responsibleDepartmentId;

    @NotNull(message = "Необходимо заполнить \"Куратор\"")
    private Integer curatorId;

    @NotEmpty(message = "Необходимо заполнить \"Адрес\"")
    private String address;
}
