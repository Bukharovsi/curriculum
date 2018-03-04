package ru.curriculum.domain.stateSchedule.dictionary.finders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.stateSchedule.entity.StudyMode;
import ru.curriculum.domain.stateSchedule.repository.StudyModeRepository;
import ru.curriculum.lib.DahmerauLevenshteinComparator;
import ru.curriculum.lib.PercentageMetric;

@Component
public class StudyModeValueFinder {
    private StudyModeRepository studyModeRepository;
    private DahmerauLevenshteinComparator comparator;

    @Autowired
    public StudyModeValueFinder(StudyModeRepository studyModeRepository) {
        this.studyModeRepository = studyModeRepository;
        this.comparator = new DahmerauLevenshteinComparator(new PercentageMetric(50));
    }

    public StudyMode find(String nameTemplate) {
        String name = nameTemplate.replaceAll("\\s+","").toLowerCase();
        for (StudyMode studyMode : studyModeRepository.findAll()) {
            String studyModeName = studyMode.name().replaceAll("\\s+","").toLowerCase();
            if(comparator.isEqual(name, studyModeName)) {
                return studyMode;
            }
        }
        return null;
    }
}
