package ru.curriculum.service.stateSchedule.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.curriculum.service.curator.dto.CuratorDto;
import ru.curriculum.service.division.DivisionDto;

import java.util.Date;
import java.util.List;

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

    private List<InternshipDto> internships;

    private Integer lernerCount;

    private Integer groupCount;

    private Integer countOfHoursPerLerner;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFinish;

    private DivisionDto responsibleDepartment;

    private CuratorDto curator;

    private String address;

    private boolean etpCreated;
}
