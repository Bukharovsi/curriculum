package ru.curriculum.service.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.domain.timetable.repository.LessonFormRepository;
import ru.curriculum.service.timetable.dto.LessonDto;
import ru.curriculum.service.timetable.dto.TimetableDto;

import java.util.Optional;

@Component
public class TimetableEditService {
    @Autowired
    private LessonFormRepository lessonFormRepository;

    public void editTimetable(Timetable timetable, TimetableDto timetableDto) {
        editLessons(timetable, timetableDto);
    }

    private void editLessons(Timetable timetable, TimetableDto timetableDto) {
        timetable.lessons().forEach(lesson -> {
            Optional<LessonDto> lessonDto = timetableDto.getLessons()
                    .stream()
                    .filter(l -> l.getId().equals(lesson.id()))
                    .findFirst();
            if(lessonDto.isPresent()) {
                LessonDto dto = lessonDto.get();
                lesson.date(dto.getDate());
                lesson.time(dto.getTime());
                lesson.lessonForm(lessonFormRepository.findOne(dto.getLessonFormId()));
                lesson.address(dto.getAddress());
                lesson.audienceNumber(dto.getAudienceNumber());
            }
        });
    }
}
