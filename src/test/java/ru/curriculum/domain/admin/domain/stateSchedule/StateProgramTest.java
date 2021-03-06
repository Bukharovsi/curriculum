package ru.curriculum.domain.admin.domain.stateSchedule;

import boot.IntegrationBoot;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.helper.UserTestFactory;
import ru.curriculum.domain.organization.entity.Division;
import ru.curriculum.domain.organization.repository.DivisionRepository;
import ru.curriculum.domain.stateSchedule.entity.Internship;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;
import ru.curriculum.domain.stateSchedule.repository.ImplementationFormRepository;
import ru.curriculum.domain.stateSchedule.repository.StateProgramRepository;
import ru.curriculum.domain.stateSchedule.repository.StudyModeRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class StateProgramTest extends IntegrationBoot {

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

    @Test
    public void afterSavingAndGettingStateProgramsAreEquals() throws Exception {

        Division mainDepartment = divisionRepository.save(new Division("main department"));

        StateProgram stateProgram = StateProgram.builder()
                .targetAudience("English teachers")
                .name("ABC")
                .mode(studyModeRepository.findOne("fulltime"))
                .implementationForm(implementationFormRepository.findOne("modular"))
                .internships(getInternship())
                .lernerCount(20)
                .groupCount(1)
                .countOfHoursPerLerner(100)
                .curator(userTestFactory.createAndSaveRandomUser())
                .dateStart(new Date())
                .dateFinish(new Date())
                .address("Kazan, main street, 1")
                .responsibleDepartment(mainDepartment)
                .build();

        stateProgramRepository.save(stateProgram);

        StateProgram storedStateProgram = stateProgramRepository.findOne(stateProgram.id());
        Assert.assertEquals(stateProgram, storedStateProgram);
    }

    private Set<Internship> getInternship() {
        Set<Internship> internships = new HashSet<>();

        Internship internship1 = Internship.builder()
                .dateStart(new Date())
                .dateFinish(new Date())
                .theme("Phrasal verbs")
                .address("Kazan, main street, 2")
                .build();

        Internship internship2 = Internship.builder()
                .dateStart(new Date())
                .dateFinish(new Date())
                .theme("Grammar")
                .address("Kazan, main street, 3")
                .build();

        internships.add(internship1);
        internships.add(internship2);

        return internships;
    }
}