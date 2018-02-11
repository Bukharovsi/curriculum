package ru.curriculum.domain.stateSchedule.dictionary;

import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.stateSchedule.entity.ImplementationForm;
import ru.curriculum.domain.stateSchedule.entity.StudyMode;

public interface IDictionaryValuesFinder {

    Curator findCurator(String fullNameTemplate);

    ImplementationForm findImplementationForm(String name);

    StudyMode findMode(String string);
}
