package ru.curriculum.service.stateSchedule.dto;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.service.user.dto.UserDTO;

import javax.persistence.*;
import java.util.Date;

/**
 * Государственная программа, на основании которой формируется УТП
 */
@Data
@Accessors(fluent = true)
@Builder
@AllArgsConstructor
@ToString
public class StateProgramCreationDto {

    private Integer id;

    @NotEmpty(message = "Необходимо заполнить \"Целевая аудитория\"")
    private String targetAudience;

    @NotEmpty(message = "Необходимо заполнить \"Название\"")
    private String name;

    @NotEmpty(message = "Необходимо заполнить \"Форма обучения\"")
    private String modeId;

    @NotEmpty(message = "Необходимо заполнить \"Форма реализации\"")
    private String implementationFormId;

    @NotEmpty(message = "Необходимо заполнить \"Кол-во слушателей\"")
    private Integer lernerCount;

    @NotEmpty(message = "Необходимо заполнить \"Кол-во групп\"")
    private Integer groupCount;

    @NotEmpty(message = "Необходимо заполнить \"Объем на одного слушателя в часах\"")
    private Integer countOfHoursPerLerner;

    @NotEmpty(message = "Необходимо заполнить \"Дата начала\"")
    private Date dateStart;

    @NotEmpty(message = "Необходимо заполнить \"Дата окончания\"")
    private Date dateFinish;

    @NotEmpty(message = "Необходимо заполнить \"Ответсвенное подразделение\"")
    private String responsibleDepartment;

    @NotEmpty(message = "Необходимо заполнить \"Куратор\"")
    private Integer curatorId;

    @NotEmpty(message = "Необходимо заполнить \"Адрес\"")
    private String address;
}
