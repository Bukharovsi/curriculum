package ru.curriculum.domain.stateSchedule.dictionary.finders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.lib.DahmerauLevenshteinComparator;
import ru.curriculum.lib.PercentageMetric;

@Component
public class CuratorValueFinder {
    private CuratorRepository curatorRepository;
    private DahmerauLevenshteinComparator comparator;

    @Autowired
    public CuratorValueFinder(CuratorRepository curatorRepository) {
        this.curatorRepository = curatorRepository;
        this.comparator = new DahmerauLevenshteinComparator(new PercentageMetric(30));
    }

    //TODO: сделать чтобы искал еще по одельности по фамилии имени и отчесту если по полному не нашел
    public Curator find(String nameTemplate) {
        String name = nameTemplate.replaceAll("[^а-яА-Я]", "").trim().toLowerCase();
        for (Curator curator : curatorRepository.findAll()) {
            String curatorName = (curator.surname().concat(curator.firstName()).concat(curator.patronymic())).trim().toLowerCase();
            if(comparator.isEqual(name, curatorName)) {
                return curator;
            }
        }

        return null;
    }
}
