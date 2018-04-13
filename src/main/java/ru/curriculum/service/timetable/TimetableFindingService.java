package ru.curriculum.service.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.repository.ETPRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class TimetableFindingService {
    //TODO: переделать на обычный запрос
    @Autowired
    private ETPRepository etpRepository;

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
