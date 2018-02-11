package ru.curriculum.domain.stateSchedule.dictionary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.domain.stateSchedule.dictionary.DictionaryValuesFinder;
import ru.curriculum.domain.stateSchedule.dictionary.DictionaryValuesStore;
import ru.curriculum.domain.stateSchedule.dictionary.IDictionaryValuesFinder;
import ru.curriculum.domain.stateSchedule.repository.ImplementationFormRepository;
import ru.curriculum.domain.stateSchedule.repository.StudyModeRepository;

@Configuration
public class DictionaryValuesFinderConfig {
    @Autowired
    private CuratorRepository curatorRepository;
    @Autowired
    private ImplementationFormRepository implementationFormRepository;
    @Autowired
    private StudyModeRepository studyModeRepository;


    @Bean
    public IDictionaryValuesFinder dictionaryValuesFinder() {
        DictionaryValuesStore dictionaryValuesStore = new DictionaryValuesStore(
                curatorRepository,
                implementationFormRepository,
                studyModeRepository
        );

        return new DictionaryValuesFinder(dictionaryValuesStore);
    }
}
