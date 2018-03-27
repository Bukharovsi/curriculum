package ru.curriculum.service.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.timetable.repository.LessonFormRepository;
import ru.curriculum.service.timetable.dto.LessonFormDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class LessonFormFindingService {
    @Autowired
    private LessonFormRepository lessonFormRepository;

    public List<LessonFormDto> findAll() {
        List<LessonFormDto> dtos = new ArrayList<>();
        lessonFormRepository.findAll().forEach(lessonForm ->
            dtos.add(new LessonFormDto(lessonForm))
        );
        return dtos;
    }
}
