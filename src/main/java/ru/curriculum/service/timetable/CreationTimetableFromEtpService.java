package ru.curriculum.service.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.service.etp.dto.ETPDto;
import ru.curriculum.service.etp.dto.PlanDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@Component
public class CreationTimetableFromEtpService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Timetable makeTimetable(ETPDto etpDto) {
        LocalDateTime beginDate = null;
        if(null != etpDto.getFullTimeLearningBeginDate()) {
            beginDate = LocalDateTime.ofInstant(etpDto.getFullTimeLearningBeginDate().toInstant(), ZoneId.systemDefault());
        }

        LocalDateTime endDate = null;
        if(null != etpDto.getFullTimeLearningEndDate()) {
            endDate = LocalDateTime.ofInstant(etpDto.getFullTimeLearningEndDate().toInstant(), ZoneId.systemDefault());
        }

        Timetable timetable = new Timetable(
                beginDate,
                endDate,
                etpDto.getTitle(),
                makeLessons(etpDto)
        );
        return timetable;
    }

    private Set<Lesson> makeLessons(ETPDto etpDto) {
        Set<Lesson> lessons = new HashSet<>();

        etpDto.getOmaModules().forEach(omaModuleDto -> {
            Lesson lesson = Lesson.builder()
                    .theme(omaModuleDto.getName())
                    .teacher(getTeacher(omaModuleDto.getPlan()))
                    .lernerCount(omaModuleDto.getPlan().getLernerCount())
                    .build();
            lessons.add(lesson);
        });

        etpDto.getEaModules().forEach(eaModuleDto ->
            eaModuleDto.getSections().forEach(eaSectionDto ->
                eaSectionDto.getTopics().forEach(eaTopicDto -> {
                    Lesson lesson = Lesson.builder()
                            .theme(eaTopicDto.getName())
                            .teacher(getTeacher(eaTopicDto.getPlan()))
                            .lernerCount(eaTopicDto.getPlan().getLernerCount())
                            .build();
                    lessons.add(lesson);
                })
            )
        );

        etpDto.getEmaModules().forEach(emaModuleDto -> {
            Lesson lesson = Lesson.builder()
                    .theme(emaModuleDto.getName())
                    .teacher(getTeacher(emaModuleDto.getPlan()))
                    .lernerCount(emaModuleDto.getPlan().getLernerCount())
                    .build();
            lessons.add(lesson);
        });

        return lessons;

    }

    private Teacher getTeacher(PlanDto planDto) {
        return planDto.hasTeacher() ? teacherRepository.findOne(planDto.getTeacherId()) : null;
    }
}
