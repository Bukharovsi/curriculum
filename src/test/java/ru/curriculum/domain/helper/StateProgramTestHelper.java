package ru.curriculum.domain.helper;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.organization.entity.Division;
import ru.curriculum.domain.organization.repository.DivisionRepository;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;
import ru.curriculum.domain.stateSchedule.repository.ImplementationFormRepository;
import ru.curriculum.domain.stateSchedule.repository.StateProgramRepository;
import ru.curriculum.domain.stateSchedule.repository.StudyModeRepository;

import java.util.Date;
import java.util.UUID;

@Component
public class StateProgramTestHelper {
    @Autowired
    private StudyModeRepository studyModeRepository;

    @Autowired
    private ImplementationFormRepository implementationFormRepository;

    @Autowired
    private UserTestFactory userTestFactory;

    @Autowired
    private StateProgramRepository stateProgramRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    public StateProgram createStateProgram() {
        Division mainDepartment = divisionRepository.save(new Division(UUID.randomUUID().toString()));

        return StateProgram.builder()
                .targetAudience("English teachers")
                .name("ABC")
                .mode(studyModeRepository.findOne("fulltime"))
                .implementationForm(implementationFormRepository.findOne("modular"))
                .lernerCount(20)
                .groupCount(1)
                .countOfHoursPerLerner(100)
                .curator(userTestFactory.createAndSaveRandomUser())
                .dateStart(new Date())
                .dateFinish(new Date())
                .address("Kazan, main street, 1")
                .responsibleDepartment(mainDepartment)
                .build();
    }

    public StateProgram createAndSaveStateProgram() {
        return stateProgramRepository.save(createStateProgram());
    }

    public void deleteAll() {
        stateProgramRepository.deleteAll();
//        divisionRepository.deleteAll();
    }
}
