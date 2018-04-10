package ru.curriculum.domain.admin.service.timetable;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.timetable.entity.SchoolDay;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.service.timetable.dto.TimetableDto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RunWith(DataProviderRunner.class)
public class TimetableDtoTest {

    @Test
    @UseDataProvider("timetableDataProvider")
    public void createDtoFromTimetable_mustCreateCorrectly(Timetable timetable) {
        TimetableDto dto = new TimetableDto(timetable);

        Assert.assertEquals(timetable.id(), dto.getId());
        Assert.assertEquals(timetable.theme(), dto.getTheme());
        Assert.assertEquals(timetable.beginDate(), dto.getBeginDate());
        Assert.assertEquals(timetable.endDate(), dto.getEndDate());
        Assert.assertEquals(timetable.schoolDays().size(), dto.getSchoolDays().size());
    }

    @DataProvider
    public static Object[][] timetableDataProvider() {
        Set<SchoolDay> shoolDays = new HashSet<>();
        shoolDays.add(new SchoolDay(LocalDate.now()));
        shoolDays.add(new SchoolDay(LocalDate.now()));

        ETP etp = new ETP();
        etp.id(1);

        return new Object[][]{
                { new Timetable(LocalDate.now(), LocalDate.now(), "Main theme", new HashSet<>(), etp) },
                { new Timetable(LocalDate.parse("2018-01-01"), LocalDate.parse("2018-12-31"), "Main theme", new HashSet<>(), etp) },
                { new Timetable(LocalDate.parse("2018-01-01"), LocalDate.parse("2018-12-31"), null, new HashSet<>(), etp) },
                { new Timetable(LocalDate.parse("2018-12-31"), LocalDate.parse("2018-12-31"), null, new HashSet<>(), etp) },
                { new Timetable(LocalDate.parse("2018-12-31"), LocalDate.parse("2018-12-31"), null, new HashSet<>(), etp) },
                { new Timetable(LocalDate.parse("2018-01-01"), LocalDate.parse("2018-12-31"), null, shoolDays, etp) }
        };
    }
}
