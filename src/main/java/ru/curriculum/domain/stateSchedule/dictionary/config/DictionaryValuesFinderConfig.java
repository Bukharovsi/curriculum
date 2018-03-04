package ru.curriculum.domain.stateSchedule.dictionary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.curriculum.domain.stateSchedule.dictionary.DictionaryValuesFinder;
import ru.curriculum.domain.stateSchedule.dictionary.IDictionaryValuesFinder;
import ru.curriculum.domain.stateSchedule.dictionary.finders.CuratorValueFinder;
import ru.curriculum.domain.stateSchedule.dictionary.finders.ImplementationFormValueFinder;
import ru.curriculum.domain.stateSchedule.dictionary.finders.ResponsibleDepartmentValuesFinder;
import ru.curriculum.domain.stateSchedule.dictionary.finders.StudyModeValueFinder;

@Configuration
public class DictionaryValuesFinderConfig {
    @Autowired
    private CuratorValueFinder curatorValueFinder;
    @Autowired
    private ImplementationFormValueFinder implementationFormValueFinder;
    @Autowired
    private ResponsibleDepartmentValuesFinder responsibleDepartmentValuesFinder;
    @Autowired
    private StudyModeValueFinder studyModeValueFinder;

    @Bean
    public IDictionaryValuesFinder dictionaryValuesFinder() {
        return new DictionaryValuesFinder(
                curatorValueFinder,
                implementationFormValueFinder,
                responsibleDepartmentValuesFinder,
                studyModeValueFinder
        );
    }
}
