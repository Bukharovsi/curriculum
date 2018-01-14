package ru.curriculum.service.stateSchedule.dto;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.service.user.dto.UserDTO;

import java.util.Date;

/**
 * Государственная программа, на основании которой формируется УТП
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StateProgramViewDto {

    private Integer id;

    private String targetAudience;

    private String name;

    private StudyModeDto mode;

    private ImplementationFormDto implementationForm;

    private Integer lernerCount;

    private Integer groupCount;

    private Integer countOfHoursPerLerner;

    private Date dateStart;

    private Date dateFinish;

    private String responsibleDepartment;

    private UserDTO curator;

    private String address;
}