package ru.curriculum.domain.admin.service.timetable;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.curriculum.domain.helper.TeacherHelper;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.domain.timetable.entity.LessonForm;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.service.timetable.dto.LessonDto;

import java.time.LocalDate;
import java.time.LocalTime;

@RunWith(DataProviderRunner.class)
public class LessonDtoTest {

    @Test
    @UseDataProvider("lessonDataProvider")
    public void createLessonDtoFromLesson_mustCreateCorrectly(Lesson lesson) {
        LessonDto dto = new LessonDto(lesson);

        Assert.assertEquals(lesson.id(), dto.getId());
        Assert.assertEquals(lesson.theme(), dto.getTheme());
        Assert.assertEquals(lesson.time(), dto.getTime());
        Assert.assertEquals(lesson.address(), dto.getAddress());
        Assert.assertEquals(lesson.audienceNumber(), dto.getAudienceNumber());
        Assert.assertEquals(lesson.lessonForm().code(), dto.getLessonFormId());
        Assert.assertEquals(lesson.lessonForm().name(), dto.getLessonFormName());
        Assert.assertEquals(lesson.lernerCount(), dto.getLernerCount());
        Assert.assertEquals(lesson.teacher().id(), dto.getTeacher().getId());
    }

    @DataProvider
    public static Object[][] lessonDataProvider() {
        return new Object[][] {
                {
                    Lesson.builder()
                            .id(1)
                            .lernerCount(10)
                            .theme("Theme")
                            .address("Street 204")
                            .audienceNumber(10)
                            .time("14:00-16:10")
                            .lessonForm(new LessonForm())
                            .teacher(new Teacher())
                            .build()
                },
                {
                        Lesson.builder()
                                .lernerCount(10)
                                .theme("Theme")
                                .address("Street 204")
                                .audienceNumber(10)
                                .time("14:00 - 16:10")
                                .lessonForm(new LessonForm("Пракика"))
                                .teacher(new TeacherHelper().getTeacher())
                                .build()
                },
                {
                        Lesson.builder()
                                .lernerCount(10)
                                .theme("Theme")
                                .address("Street 204")
                                .audienceNumber(10)
                                .lessonForm(new LessonForm("Пракика"))
                                .teacher(new TeacherHelper().getTeacher())
                                .build()
                },
        };
    }
}
