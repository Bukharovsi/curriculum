package ru.curriculum.service.stateSchedule.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.repository.ETPRepository;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;
import ru.curriculum.service.curator.dto.CuratorDto;
import ru.curriculum.service.division.DivisionDto;
import ru.curriculum.service.stateSchedule.dto.ImplementationFormDto;
import ru.curriculum.service.stateSchedule.dto.StateProgramCreationDto;
import ru.curriculum.service.stateSchedule.dto.StateProgramViewDto;
import ru.curriculum.service.stateSchedule.dto.StudyModeDto;

@Component
public class StateScheduleEntityToDtoConverter {
    @Autowired
    private ETPRepository etpRepository;


    public StateProgramViewDto makeViewDto(StateProgram stateProgram) {
        boolean eptIsCreated =
                null != stateProgram.id() && null != etpRepository.findByStateProgramId(stateProgram.id())
                        ? true : false;

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
                .etpCreated(eptIsCreated)
                .build();
        if (stateProgram.curator() != null) {
            stateProgramDto.setCurator(new CuratorDto(stateProgram.curator()));
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
