package ru.curriculum.domain.admin.service.timetable;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.curriculum.domain.admin.domain.etp.ETPMock;
import ru.curriculum.domain.etp.repository.ETPRepository;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.service.etp.dto.ETPDto;
import ru.curriculum.service.timetable.CreationTimetableFromEtpService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CreationTimetableFromEtpServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private ETPRepository etpRepository;

    @InjectMocks
    private CreationTimetableFromEtpService creationTimetableFromEtpService;

    //TODO: Зачем создавать с пустой ДТОшки
    @Test
    @Ignore
    public void makeTimetableFromEmptyEtpDto_mustCreateEmptyTimetableCorrectly() {
        ETPDto etpDto = new ETPDto();

        Timetable timetable = creationTimetableFromEtpService.makeTimetable(etpDto);

        assertNull(timetable.id());
        assertNull(timetable.theme());
        assertNull(timetable.beginDate());
        assertNull(timetable.endDate());
        assertEquals(0, timetable.schoolDays().size());
    }

    @Test
    public void makeTimetableFromEtpDto_mustCreateCorrectly() {
        ETPDto etpDto = new ETPMock().getETPDto();
        etpDto.setFullTimeLearningBeginDate(Date.from(LocalDate.parse("2018-01-01").atStartOfDay(ZoneId.systemDefault()).toInstant()));
        etpDto.setFullTimeLearningEndDate(Date.from(LocalDate.parse("2018-01-10").atStartOfDay(ZoneId.systemDefault()).toInstant()));

        Timetable timetable = creationTimetableFromEtpService.makeTimetable(etpDto);

        assertNull(timetable.id());
        assertEquals(etpDto.getTitle(), timetable.theme());
        assertEquals(etpDto.getFullTimeLearningBeginDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), timetable.beginDate());
        assertEquals(etpDto.getFullTimeLearningEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), timetable.endDate());
    }
}
