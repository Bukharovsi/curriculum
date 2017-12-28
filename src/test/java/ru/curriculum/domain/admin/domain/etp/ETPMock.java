package ru.curriculum.domain.admin.domain.etp;

import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.educationActivityModule.EAModule;
import ru.curriculum.domain.etp.entity.educationActivityModule.EASection;
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

    public EAModule getEducationModule() {
        EAModule module = new EAModule("Модуль", new HashSet<>());

        return module;
    }

    public EAModule getFullEducationModule() {
        return new EAModule(
                "Модуль",
                new HashSet<EASection>(){{add(getSection());}});
    }

    public EASection getSection() {
        EASection section = new EASection("Раздел", getPlan());

        return section;
    }

    public Plan getPlan() {
        return new Plan(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 6.0, 7.0, null);

    }

    public ETP_DTO getETP_DTO() {
        ETP etp = getETP();
        etp.addModule(getFullEducationModule());

        return new ETP_DTO(etp);
    }
}
