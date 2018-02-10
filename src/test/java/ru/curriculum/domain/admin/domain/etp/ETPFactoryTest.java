package ru.curriculum.domain.admin.domain.etp;

import org.junit.Assert;
import org.junit.Test;
import ru.curriculum.domain.etp.ETPFactory;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;

import java.util.Date;

public class ETPFactoryTest {
    private ETPFactory etpFactory;

    public ETPFactoryTest() {
        this.etpFactory = new ETPFactory();
    }

    @Test
    public void makeETPTemplateFromStateProgram_mustBeCreateCorrectly() {
        StateProgram program = getStateProgram();
        ETP etpTemplate = etpFactory.makeETPTemplateFromStateProgram(program);

        assertETPAndStateProgramEquals(program, etpTemplate);
    }

    private void assertETPAndStateProgramEquals(StateProgram program, ETP etpTemplate) {
        Assert.assertEquals(program.name(), etpTemplate.title());
        Assert.assertEquals(program.dateStart(), etpTemplate.fullTimeLearningBeginDate());
        Assert.assertEquals(program.dateFinish(), etpTemplate.fullTimeLearningEndDate());
    }


    private StateProgram getStateProgram() {
        StateProgram program = StateProgram.builder()
                .name("Информационные технологии")
                .dateStart(new Date(1))
                .dateFinish(new Date(5))
                .build();

        return program;
    }
}
