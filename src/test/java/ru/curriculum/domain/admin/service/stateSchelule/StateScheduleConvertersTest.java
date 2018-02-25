package ru.curriculum.domain.admin.service.stateSchelule;

import boot.IntegrationBoot;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.helper.StateProgramTestHelper;
import ru.curriculum.domain.helper.UserTestFactory;
import ru.curriculum.domain.stateSchedule.entity.Internship;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;
import ru.curriculum.service.stateSchedule.converter.DtoToStateScheduleConverter;
import ru.curriculum.service.stateSchedule.converter.StateScheduleEntityToDtoConverter;
import ru.curriculum.service.stateSchedule.dto.InternshipDto;
import ru.curriculum.service.stateSchedule.dto.StateProgramCreationDto;
import ru.curriculum.service.stateSchedule.dto.StateProgramViewDto;

import java.util.Collection;
import java.util.Date;

import static java.util.stream.Collectors.toList;

public class StateScheduleConvertersTest extends IntegrationBoot{

    @Autowired
    private DtoToStateScheduleConverter dtoToStateScheduleConverter;

    @Autowired
    private StateScheduleEntityToDtoConverter stateScheduleEntityToDtoConverter;

    @Autowired
    private UserTestFactory userTestFactory;

    @Autowired
    private StateProgramTestHelper stateProgramTestHelper;

    @Test
    public void whenDtoConvertsToEntityAndToDtoThenDTOsTheSame() {
        Curator curator = userTestFactory.createAndSaveRandomUser();
        Collection<Internship> internships = stateProgramTestHelper.createInternships();

        StateProgramCreationDto newStateProgram = StateProgramCreationDto.builder()
            .address("Kazan, main street 1")
            .countOfHoursPerLerner(10)
            .curatorId(curator.id())
            .dateStart(new Date())
            .dateFinish(new Date())
            .groupCount(2)
            .implementationFormId("modular")
                .internships(internships.stream().map(InternshipDto::new).collect(toList()))
            .lernerCount(10)
            .modeId("fulltime")
            .name("some name")
            .responsibleDepartmentId(1)
            .targetAudience("teachers")
            .build();

        StateProgram stateProgramEntity = dtoToStateScheduleConverter.createBasedOnDto(newStateProgram);

        StateProgramViewDto viewDto = stateScheduleEntityToDtoConverter.makeViewDto(stateProgramEntity);
        Assert.assertEquals(stateProgramEntity.id(), viewDto.getId());
        Assert.assertEquals(newStateProgram.getAddress(), viewDto.getAddress());
        Assert.assertEquals(newStateProgram.getCountOfHoursPerLerner(), viewDto.getCountOfHoursPerLerner());
        Assert.assertEquals(newStateProgram.getCuratorId(), viewDto.getCurator().getId());
        Assert.assertEquals(stateProgramEntity.curator().login(), viewDto.getCurator().getUsername());
        Assert.assertEquals(newStateProgram.getDateStart(), viewDto.getDateStart());
        Assert.assertEquals(newStateProgram.getDateFinish(), viewDto.getDateFinish());
        Assert.assertEquals(newStateProgram.getGroupCount(), viewDto.getGroupCount());
        Assert.assertEquals(newStateProgram.getImplementationFormId(), viewDto.getImplementationForm().getId());
        Assert.assertEquals(stateProgramEntity.implementationForm().name(), viewDto.getImplementationForm().getName());
        Assert.assertEquals(newStateProgram.getLernerCount(), viewDto.getLernerCount());
        Assert.assertEquals(newStateProgram.getModeId(), viewDto.getMode().getId());
        Assert.assertEquals(stateProgramEntity.mode().name(), viewDto.getMode().getName());
        Assert.assertEquals(newStateProgram.getName(), viewDto.getName());
        Assert.assertEquals(newStateProgram.getResponsibleDepartmentId(), viewDto.getResponsibleDepartment().getId());
        Assert.assertEquals(newStateProgram.getTargetAudience(), viewDto.getTargetAudience());
        Assert.assertTrue(newStateProgram.getInternships().containsAll(viewDto.getInternships()));
        Assert.assertFalse(viewDto.isEtpCreated());
    }
}
