package ru.curriculum.domain.admin.domain.stateSchedule;

import org.junit.Test;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;

public class StateProgramTest {

    @Test
    public void afterSavingAndGettingStateProgramsAreEquals() throws Exception {
        StateProgram stateProgram = StateProgram.builder()
            .targetAudience("English teachers")
            .name("ABC")
            .mode()
    }
}