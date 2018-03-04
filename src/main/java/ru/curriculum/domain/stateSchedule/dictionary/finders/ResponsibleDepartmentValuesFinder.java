package ru.curriculum.domain.stateSchedule.dictionary.finders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.organization.entity.Division;
import ru.curriculum.domain.organization.repository.DivisionRepository;
import ru.curriculum.lib.DahmerauLevenshteinComparator;
import ru.curriculum.lib.PercentageMetric;

@Component
public class ResponsibleDepartmentValuesFinder {
    private DivisionRepository divisionRepository;
    private DahmerauLevenshteinComparator comparator;

    @Autowired
    public ResponsibleDepartmentValuesFinder(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
        this.comparator = new DahmerauLevenshteinComparator(new PercentageMetric(50));
    }

    public Division find(String nameTemplate) {
        String name = nameTemplate.replaceAll("\\s+","").toLowerCase();
        for (Division division : divisionRepository.findAll()) {
            String divisionName = division.name().replaceAll("\\s+","").toLowerCase();
            if(comparator.isEqual(name, divisionName)) {
                return division;
            }
        }
        return null;
    }
}
