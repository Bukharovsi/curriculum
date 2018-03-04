package ru.curriculum.domain.stateSchedule.dictionary;

import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.organization.entity.Division;
import ru.curriculum.domain.stateSchedule.dictionary.finders.CuratorValueFinder;
import ru.curriculum.domain.stateSchedule.dictionary.finders.ImplementationFormValueFinder;
import ru.curriculum.domain.stateSchedule.dictionary.finders.ResponsibleDepartmentValuesFinder;
import ru.curriculum.domain.stateSchedule.dictionary.finders.StudyModeValueFinder;
import ru.curriculum.domain.stateSchedule.entity.ImplementationForm;
import ru.curriculum.domain.stateSchedule.entity.StudyMode;

public class DictionaryValuesFinder implements IDictionaryValuesFinder {
    private CuratorValueFinder curatorValueFinder;
    private ImplementationFormValueFinder implementationFormValueFinder;
    private ResponsibleDepartmentValuesFinder responsibleDepartmentValuesFinder;
    private StudyModeValueFinder studyModeValueFinder;

    public DictionaryValuesFinder(
            CuratorValueFinder curatorValueFinder,
            ImplementationFormValueFinder implementationFormValueFinder,
            ResponsibleDepartmentValuesFinder responsibleDepartmentValuesFinder,
            StudyModeValueFinder studyModeValueFinder
    ) {
        this.curatorValueFinder = curatorValueFinder;
        this.implementationFormValueFinder = implementationFormValueFinder;
        this.responsibleDepartmentValuesFinder = responsibleDepartmentValuesFinder;
        this.studyModeValueFinder = studyModeValueFinder;
    }

    @Override
    public Curator findCurator(String fullNameTemplate) {
        return curatorValueFinder.find(fullNameTemplate);
    }

    @Override
    public ImplementationForm findImplementationForm(String nameTemplate) {
        return implementationFormValueFinder.find(nameTemplate);
    }

    @Override
    public StudyMode findMode(String nameTemplate) {
        return studyModeValueFinder.find(nameTemplate);
    }

    @Override
    public Division findResponsibleDepartment(String nameTemplate) {
        return responsibleDepartmentValuesFinder.find(nameTemplate);
    }
}
