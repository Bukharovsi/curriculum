package ru.curriculum.service.stateSchedule.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.repository.ETPRepository;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;
import ru.curriculum.service.curator.dto.CuratorDto;
import ru.curriculum.service.division.DivisionDto;
import ru.curriculum.service.stateSchedule.dto.*;

import static java.util.stream.Collectors.toList;

@Component
public class StateScheduleEntityToDtoConverter {
    @Autowired
    private ETPRepository etpRepository;


    public StateProgramViewDto makeViewDto(StateProgram stateProgram) {
        boolean eptIsCreated =
                null != stateProgram.id() && null != etpRepository.findByStateProgramId(stateProgram.id())
                        ? true : false;

        DivisionDto divisionDto = null != stateProgram.responsibleDepartment() ? new DivisionDto(stateProgram.responsibleDepartment()): null;
        StudyModeDto studyModeDto = null != stateProgram.mode() ? new StudyModeDto(stateProgram.mode().id(), stateProgram.mode().name()) : null;
        ImplementationFormDto implementationFormDto = null != stateProgram.implementationForm()
                ? new ImplementationFormDto(stateProgram.implementationForm().id(), stateProgram.implementationForm().name())
                : null;

        StateProgramViewDto stateProgramDto = StateProgramViewDto.builder()
                .id(stateProgram.id())
                .name(stateProgram.name())
                .targetAudience(stateProgram.targetAudience())
                .dateStart(stateProgram.dateStart())
                .dateFinish(stateProgram.dateFinish())
                .address(stateProgram.address())
                .groupCount(stateProgram.groupCount())
                .id(stateProgram.id())
                .countOfHoursPerLerner(stateProgram.countOfHoursPerLerner())
                .lernerCount(stateProgram.lernerCount())
                .responsibleDepartment(divisionDto)
                .mode(studyModeDto)
                .implementationForm(implementationFormDto)
                .internships(stateProgram.internships().stream().map(InternshipDto::new).collect(toList()))
                .etpCreated(eptIsCreated)
                .build();
        if (stateProgram.curator() != null) {
            stateProgramDto.setCurator(new CuratorDto(stateProgram.curator()));
        }

        return stateProgramDto;
    }

    public StateProgramCreationDto makeEditDto(StateProgram stateProgram) {
        Integer responsibleDepartmentId = null != stateProgram.responsibleDepartment() ? stateProgram.responsibleDepartment().id(): null;
        String studyModeId = null != stateProgram.mode() ? stateProgram.mode().id() : null;
        String implementationFormId = null != stateProgram.implementationForm() ? stateProgram.implementationForm().id() : null;
        Integer curatorId = null != stateProgram.curator() ? stateProgram.curator().id() : null;

        StateProgramCreationDto stateProgramDto = StateProgramCreationDto.builder()
                .id(stateProgram.id())
                .name(stateProgram.name())
                .targetAudience(stateProgram.targetAudience())
                .dateStart(stateProgram.dateStart())
                .dateFinish(stateProgram.dateFinish())
                .address(stateProgram.address())
                .groupCount(stateProgram.groupCount())
                .id(stateProgram.id())
                .countOfHoursPerLerner(stateProgram.countOfHoursPerLerner())
                .lernerCount(stateProgram.lernerCount())
                .responsibleDepartmentId(responsibleDepartmentId)
                .internships(stateProgram.internships().stream().map(InternshipDto::new).collect(toList()))
                .modeId(studyModeId)
                .implementationFormId(implementationFormId)
                .curatorId(curatorId)
                .build();

        return stateProgramDto;
    }
}
