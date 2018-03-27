package ru.curriculum.domain.admin.service.timetable;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.service.timetable.dto.TimetableDto;

import java.time.LocalDateTime;
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
        Assert.assertEquals(timetable.lessons().size(), dto.getLessons().size());
    }

    @DataProvider
    public static Object[][] timetableDataProvider() {
        Set<Lesson> lessons = new HashSet<>();
        lessons.add(new Lesson());
        lessons.add(new Lesson());

        ETP etp = new ETP();
        etp.id(1);

        return new Object[][]{
                { new Timetable(LocalDateTime.now(), LocalDateTime.now(), "Main theme", new HashSet<>(), etp) },
                { new Timetable(LocalDateTime.parse("2018-01-01T00:00"), LocalDateTime.parse("2018-12-31T23:59"), "Main theme", new HashSet<>(), etp) },
                { new Timetable(LocalDateTime.parse("2018-01-01T00:00"), LocalDateTime.parse("2018-12-31T23:59"), null, new HashSet<>(), etp) },
                { new Timetable(null, LocalDateTime.parse("2018-12-31T23:59"), null, new HashSet<>(), etp) },
                { new Timetable(LocalDateTime.parse("2018-12-31T23:59"), null, null, new HashSet<>(), etp) },
                { new Timetable(null, null, null, lessons, etp) }
        };
    }
}
