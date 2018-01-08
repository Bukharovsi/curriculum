package ru.curriculum.service.stateSchedule.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.domain.admin.user.repository.UserRepository;
import ru.curriculum.domain.stateSchedule.entity.ImplementationForm;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;
import ru.curriculum.domain.stateSchedule.entity.StudyMode;
import ru.curriculum.domain.stateSchedule.repository.ImplementationFormRepository;
import ru.curriculum.domain.stateSchedule.repository.StudyModeRepository;
import ru.curriculum.service.stateSchedule.dto.StateProgramCreationDto;

@Component
public class DtoToStateScheduleConverter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudyModeRepository studyModeRepository;

    @Autowired
    private ImplementationFormRepository implementationFormRepository;

    public StateProgram createBasedOnDto(StateProgramCreationDto stateProgramCreationDto) {

        User curator = userRepository.findOne(stateProgramCreationDto.curatorId());
        ImplementationForm implementationForm = implementationFormRepository.findOne(stateProgramCreationDto.implementationFormId());
        StudyMode studyMode = studyModeRepository.findOne(stateProgramCreationDto.modeId());

        StateProgram newStateProgram = StateProgram.builder()
            .name(stateProgramCreationDto.name())
            .curator(curator)
            .mode(studyMode)
            .implementationForm(implementationForm)
            .dateStart(stateProgramCreationDto.dateStart())
            .dateFinish(stateProgramCreationDto.dateFinish())
            .lernerCount(stateProgramCreationDto.lernerCount())
            .groupCount(stateProgramCreationDto.groupCount())
            .countOfHoursPerLerner(stateProgramCreationDto.countOfHoursPerLerner())
            .responsibleDepartment(stateProgramCreationDto.responsibleDepartment())
            .address(stateProgramCreationDto.address())
            .targetAudience(stateProgramCreationDto.targetAudience())
            .build();

        return newStateProgram;
    }
}
