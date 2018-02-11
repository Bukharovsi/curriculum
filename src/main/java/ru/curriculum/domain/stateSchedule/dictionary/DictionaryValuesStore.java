package ru.curriculum.domain.stateSchedule.dictionary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.domain.stateSchedule.entity.ImplementationForm;
import ru.curriculum.domain.stateSchedule.entity.StudyMode;
import ru.curriculum.domain.stateSchedule.repository.ImplementationFormRepository;
import ru.curriculum.domain.stateSchedule.repository.StudyModeRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Accessors(fluent = true)
public class DictionaryValuesStore {
    private CuratorRepository curatorRepository;
    private ImplementationFormRepository implementationFormRepository;
    private StudyModeRepository studyModeRepository;


    public List<Curator> curators()  {
        return (List<Curator>) curatorRepository.findAll();
    }

    public List<ImplementationForm> implementationForms() {
        return (List<ImplementationForm>) implementationFormRepository.findAll();
    }

    public List<StudyMode> studyModes() {
        return (List<StudyMode>) studyModeRepository.findAll();
    }
}
