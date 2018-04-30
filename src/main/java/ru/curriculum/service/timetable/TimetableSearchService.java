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

@Component
public class TimetableSearchService {
    //TODO: переделать на обычный запрос
    @Autowired
    private ETPRepository etpRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public TeacherDto getTeacherDefineInEtpTheme(Integer etpId, String themeInTimetable) {
        Teacher teacher = teacherRepository.findTeacherDefineInEtpTheme(etpId, themeInTimetable);
        return null != teacher ? new TeacherDto(teacher) : null;
    }

    public List<String> findLessonThemesAllByEtpId(Integer etpId) {
        ETP etp = etpRepository.findOne(etpId);
        return makeLessonThemesFromEtp(etp);
    }

    private List<String> makeLessonThemesFromEtp(ETP etp) {
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
