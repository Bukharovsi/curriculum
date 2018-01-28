package ru.curriculum.service.stateSchedule.converter;

import org.springframework.stereotype.Component;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;
import ru.curriculum.service.curator.dto.CuratorDTO;
import ru.curriculum.service.division.DivisionDto;
import ru.curriculum.service.stateSchedule.dto.ImplementationFormDto;
import ru.curriculum.service.stateSchedule.dto.StateProgramCreationDto;
import ru.curriculum.service.stateSchedule.dto.StateProgramViewDto;
import ru.curriculum.service.stateSchedule.dto.StudyModeDto;

@Component
public class StateScheduleEntityToDtoConverter {

    public StateProgramViewDto makeViewDto(StateProgram stateProgram) {
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
                .responsibleDepartment(new DivisionDto(stateProgram.responsibleDepartment()))
                .mode(new StudyModeDto(stateProgram.mode().id(), stateProgram.mode().name()))
                .implementationForm(
                        new ImplementationFormDto(
                                stateProgram.implementationForm().id(),
                                stateProgram.implementationForm().name()
                        )
                )
                .build();
        if (stateProgram.curator() != null) {
            stateProgramDto.setCurator(new CuratorDTO(stateProgram.curator()));
        }

        return stateProgramDto;
    }

    public StateProgramCreationDto makeEditDto(StateProgram stateProgram) {
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
                .responsibleDepartmentId(stateProgram.responsibleDepartment().id())
                .modeId(stateProgram.mode().id())
                .implementationFormId(stateProgram.implementationForm().id())
                .curatorId(stateProgram.curator().id())
                .build();

        if (stateProgram.curator() != null) {
            stateProgramDto.setCuratorId(stateProgram.curator().id());
        }

        return stateProgramDto;
    }
}
