package ru.curriculum.service.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.repository.ETPRepository;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.service.timetable.dto.TeacherDto;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class TimetableSearchService {
    @Autowired
    private ETPRepository etpRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherDto> getTeacherDefineInEtpTheme(Integer etpId, String themeInTimetable) {
        List<Teacher> teachers = teacherRepository.findTeacherDefineInEtpTheme(etpId, themeInTimetable);
        return teachers.stream().map(TeacherDto::new).collect(toList());
    }

    public List<String> findLessonThemesAllByEtpId(Integer etpId) {
        ETP etp = etpRepository.findOne(etpId);
        return createLessonThemesFromEtp(etp);
    }

    private List<String> createLessonThemesFromEtp(ETP etp) {
        List<String> themes = new ArrayList<>();
        etp.emaModules().forEach(module -> themes.add(module.name()));
        etp.omaModules().forEach(module -> themes.add(module.name()));
        etp.eaModules().forEach(eaModule ->
                eaModule.sections().forEach(section ->
                        section.topics().forEach(topic ->
                                themes.add(topic.name())
                        )
                )
        );
        return themes;
    }
}
