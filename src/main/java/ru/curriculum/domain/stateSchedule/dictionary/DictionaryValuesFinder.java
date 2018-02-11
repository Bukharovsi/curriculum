package ru.curriculum.domain.stateSchedule.dictionary;

import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.stateSchedule.entity.ImplementationForm;
import ru.curriculum.domain.stateSchedule.entity.StudyMode;

public class DictionaryValuesFinder implements IDictionaryValuesFinder {
    private DictionaryValuesStore dictionaryValuesStore;

    public DictionaryValuesFinder(DictionaryValuesStore dictionaryValuesStore) {
        this.dictionaryValuesStore = dictionaryValuesStore;
    }

    @Override
    public Curator findCurator(String fullNameTemplate) {
        String name = fullNameTemplate.replaceAll("[^а-яА-Я]", "").trim().toLowerCase();
        for (Curator curator : dictionaryValuesStore.curators()) {
            String curatorName = (curator.surname().concat(curator.firstName()).concat(curator.patronymic())).trim().toLowerCase();
            if(name.equals(curatorName)) {
                return curator;
            }
        }

        return null;
    }

    @Override
    public ImplementationForm findImplementationForm(String nameTemplate) {
        String name = nameTemplate.replaceAll("\\s+","").toLowerCase();
        for (ImplementationForm implementationForm : dictionaryValuesStore.implementationForms()) {
            String implementationFormName = implementationForm.name().replaceAll("\\s+","").toLowerCase();
            if(name.equals(implementationFormName)) {
                return implementationForm;
            }
        }

        return null;
    }

    @Override
    public StudyMode findMode(String nameTemplate) {
        String name = nameTemplate.replaceAll("\\s+","").toLowerCase();
        for (StudyMode studyMode : dictionaryValuesStore.studyModes()) {
            String studyModeName = studyMode.name().replaceAll("\\s+","").toLowerCase();
            if(name.equals(studyModeName)) {
                return studyMode;
            }
        }

        return null;
    }
}
