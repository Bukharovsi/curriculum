package ru.curriculum.domain.stateSchedule.stateProgramFileParser;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.organization.entity.Division;
import ru.curriculum.domain.stateSchedule.entity.ImplementationForm;
import ru.curriculum.domain.stateSchedule.entity.Internship;
import ru.curriculum.domain.stateSchedule.entity.StudyMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Accessors(fluent = true)
public class StateProgramTemplate {
    private Integer id;

    private String targetAudience;

    private String name;

    private StudyMode mode;

    private ImplementationForm implementationForm;

    private List<Internship> internships;

    private Integer lernerCount;

    private Integer groupCount;

    private Integer countOfHoursPerLerner;

    private Date dateStart;

    private Date dateFinish;

    private Division responsibleDepartment;

    private Curator curator;

    private String address;

    public StateProgramTemplate() {
        this.internships = new ArrayList<>();
    }
}
