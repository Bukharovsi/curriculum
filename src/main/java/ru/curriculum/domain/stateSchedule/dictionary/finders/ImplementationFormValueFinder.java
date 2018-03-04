package ru.curriculum.domain.stateSchedule.dictionary.finders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.stateSchedule.entity.ImplementationForm;
import ru.curriculum.domain.stateSchedule.repository.ImplementationFormRepository;
import ru.curriculum.lib.DahmerauLevenshteinComparator;

@Component
public class ImplementationFormValueFinder {
    private ImplementationFormRepository implementationFormRepository;
    private DahmerauLevenshteinComparator comparator;

    @Autowired
    public ImplementationFormValueFinder(ImplementationFormRepository implementationFormRepository) {
        this.implementationFormRepository = implementationFormRepository;
        this.comparator = new DahmerauLevenshteinComparator(50);
    }

    public ImplementationForm find(String nameTemplate) {
        String name = nameTemplate.replaceAll("\\s+","").toLowerCase();
        for (ImplementationForm implementationForm : implementationFormRepository.findAll()) {
            String implementationFormName = implementationForm.name().replaceAll("\\s+","").toLowerCase();
            if(comparator.isEqual(implementationFormName, name)) {
                return implementationForm;
            }
        }
        return null;
    }
}
