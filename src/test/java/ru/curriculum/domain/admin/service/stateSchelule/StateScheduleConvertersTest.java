package ru.curriculum.domain.admin.service.stateSchelule;

import boot.IntegrationBoot;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;
import ru.curriculum.service.stateSchedule.converter.DtoToStateScheduleConverter;
import ru.curriculum.service.stateSchedule.converter.StateScheduleEntityToDtoConverter;
import ru.curriculum.service.stateSchedule.dto.StateProgramCreationDto;
import ru.curriculum.service.stateSchedule.dto.StateProgramViewDto;

import java.util.Date;

public class StateScheduleConvertersTest extends IntegrationBoot{

    @Autowired
    private DtoToStateScheduleConverter dtoToStateScheduleConverter;

    @Autowired
    private StateScheduleEntityToDtoConverter stateScheduleEntityToDtoConverter;

    @Test
    public void whenDtoConvertsToEntityAndToDtoThenDTOsTheSame() throws Exception {
        StateProgramCreationDto newStateProgram = StateProgramCreationDto.builder()
            .address("Kazan, main street 1")
            .countOfHoursPerLerner(10)
            .curatorId(1)
            .dateStart(new Date())
            .dateFinish(new Date())
            .groupCount(2)
            .implementationFormId("modular")
            .lernerCount(10)
            .modeId("fulltime")
            .name("some name")
            .responsibleDepartment("some department")
            .targetAudience("teachers")
            .build();

        StateProgram stateProgramEntity = dtoToStateScheduleConverter.createBasedOnDto(newStateProgram);

        StateProgramViewDto viewDto = stateScheduleEntityToDtoConverter.makeDto(stateProgramEntity);
        Assert.assertEquals(stateProgramEntity.id(), viewDto.id());
        Assert.assertEquals(newStateProgram.address(), viewDto.address());
        Assert.assertEquals(newStateProgram.countOfHoursPerLerner(), viewDto.countOfHoursPerLerner());
        Assert.assertEquals(newStateProgram.curatorId(), viewDto.curator().getId());
        Assert.assertEquals(stateProgramEntity.curator().username(), viewDto.curator().getUsername());
        Assert.assertEquals(newStateProgram.dateStart(), viewDto.dateStart());
        Assert.assertEquals(newStateProgram.dateFinish(), viewDto.dateFinish());
        Assert.assertEquals(newStateProgram.groupCount(), viewDto.groupCount());
        Assert.assertEquals(newStateProgram.implementationFormId(), viewDto.implementationForm().id());
        Assert.assertEquals(stateProgramEntity.implementationForm().name(), viewDto.implementationForm().name());
        Assert.assertEquals(newStateProgram.lernerCount(), viewDto.lernerCount());
        Assert.assertEquals(newStateProgram.modeId(), viewDto.mode().id());
        Assert.assertEquals(stateProgramEntity.mode().name(), viewDto.mode().name());
        Assert.assertEquals(newStateProgram.name(), viewDto.name());
        Assert.assertEquals(newStateProgram.responsibleDepartment(), viewDto.responsibleDepartment());
        Assert.assertEquals(newStateProgram.targetAudience(), viewDto.targetAudience());
    }
}
