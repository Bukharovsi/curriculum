package ru.curriculum.domain.admin.domain.etp;

import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.educationActivityModule.EducationActivityModule;
import ru.curriculum.domain.etp.entity.educationActivityModule.EducationActivitySection;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.service.etp.dto.ETP_DTO;

import java.util.Date;
import java.util.HashSet;

public class ETPMock {

    public ETP getETP() {
        return new ETP(
                "Учебный план",
                "Научить",
                new Date(),
                new Date(),
                new Date(),
                new Date());
    }

    public EducationActivityModule getEducationModule() {
        EducationActivityModule module = new EducationActivityModule("Модуль", new HashSet<>());

        return module;
    }

    public EducationActivityModule getFullEducationModule() {
        return new EducationActivityModule(
                "Модуль",
                new HashSet<EducationActivitySection>(){{add(getSection());}});
    }

    public EducationActivitySection getSection() {
        EducationActivitySection section = new EducationActivitySection("Раздел", getPlan());

        return section;
    }

    public Plan getPlan() {
        return new Plan(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 6.0,7.0);

    }

    public ETP_DTO getETP_DTO() {
        ETP etp = getETP();
        etp.addModule(getFullEducationModule());

        return new ETP_DTO(etp);
    }
}
