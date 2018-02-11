package ru.curriculum.domain.admin.domain.stateSchedule;

import org.junit.Assert;
import org.junit.Test;
import ru.curriculum.domain.stateSchedule.stateProgramFileParser.StateProgramDateParser;

import java.util.Date;

public class StateProgramDateParserTest {

    @Test
    public void makeFirstDayOfMonthDate() {
        Date date = new StateProgramDateParser().makeStartDate(2018, 1);

        Assert.assertNotNull(date);
    }

    @Test
    public void makeLastDayOfMonthDate() {
        Date date = new StateProgramDateParser().makeEndDate(2018, 1);

        Assert.assertNotNull(date);
    }
}
