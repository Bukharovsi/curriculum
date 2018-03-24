package ru.curriculum.domain.admin.service.timetable;

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

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CreationTimetableFromEtpServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private ETPRepository etpRepository;

    @InjectMocks
    private CreationTimetableFromEtpService creationTimetableFromEtpService;

    @Test
    public void makeTimetableFromEmptyEtpDto_mustCreateEmptyTimetableCorrectly() {
        ETPDto etpDto = new ETPDto();

        Timetable timetable = creationTimetableFromEtpService.makeTimetable(etpDto);

        assertNull(timetable.id());
        assertNull(timetable.theme());
        assertNull(timetable.beginDate());
        assertNull(timetable.endDate());
        assertEquals(0, timetable.lessons().size());
    }

    @Test
    public void makeTimetableFromEtpDto_mustCreateCorrectly() {
        ETPDto etpDto = new ETPMock().getETPDto();

        Timetable timetable = creationTimetableFromEtpService.makeTimetable(etpDto);

        assertNull(timetable.id());
        assertEquals(etpDto.getTitle(), timetable.theme());
        assertEquals(LocalDateTime.ofInstant(etpDto.getFullTimeLearningBeginDate().toInstant(), ZoneId.systemDefault()), timetable.beginDate());
        assertEquals(LocalDateTime.ofInstant(etpDto.getFullTimeLearningEndDate().toInstant(), ZoneId.systemDefault()), timetable.endDate());
    }
}
