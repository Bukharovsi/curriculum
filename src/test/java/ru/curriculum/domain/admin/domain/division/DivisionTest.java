package ru.curriculum.domain.admin.domain.division;

import boot.IntegrationBoot;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.organization.entity.Division;
import ru.curriculum.domain.organization.repository.DivisionRepository;

public class DivisionTest extends IntegrationBoot {

    @Autowired
    DivisionRepository repository;

    @Test
    public void divisionSavesCorrectly() {
        Division division = new Division("Отдел контроля качества");
        Division savedDivision = repository.save(division);

        Assert.assertNotNull(savedDivision.id());
        Assert.assertEquals(savedDivision.name(), division.name());
    }
}
