package ru.curriculum.domain.admin.domain.etp;

import ru.curriculum.domain.directories.academicDegree.AcademicDegree;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.domain.etp.entity.educationActivity.EAModule;
import ru.curriculum.domain.etp.entity.educationActivity.EASection;
import ru.curriculum.domain.etp.entity.educationActivity.EATopic;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMAModule;
import ru.curriculum.domain.etp.entity.financingSource.FinancingSource;
import ru.curriculum.domain.etp.entity.organizationMethodicalActivity.OMAModule;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.entity.TeacherType;
import ru.curriculum.service.etp.dto.ETPDto;
import ru.curriculum.service.etp.dto.PlanDto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class ETPMock {
    private Plan plan;

    public ETPMock() {
        plan = getPlan();
    }

    public ETP getETP() {
        ETP etp = new ETP(
                "Учебный план",
                "Научить",
                new Date(1),
                new Date(2),
                new Date(3),
                new Date(4),
                FinancingSource.BUDGET,
                getEAModules(),
                getEMAModules(),
                getOMAModules()
        );

        return etp;
    }

    public Plan getPlan() {
        return new Plan(
                null,
                1.0,
                2.0,
                3.0,
                4.0,
                5.0,
                6.0,
                7.0,
                6.0,
                new Integer(1),
                new Integer(2),
                new Integer(3),
                3.0,
                getTeacher()
        );
    }

    public void applyNewPlanForModules(Plan plan) {
        this.plan = plan;
    }

    public Teacher getTeacher() {
        return new Teacher(
                1,
                "Иванов",
                "Иван",
                "Иванович",
                new AcademicDegree("ph_d", "Доктор наук"),
                "Ирорт" ,
                "Преподователь",
                TeacherType.INVITED
        );
    }

    public Set<EAModule> getEAModules() {
        Set<EAModule> modules = new HashSet<>();
        EAModule eaModule = new EAModule("Модуль учебной деятельности", getEASections());
        modules.add(eaModule);

        return modules;
    }

    public Set<EMAModule> getEMAModules() {
        Set<EMAModule> modules = new HashSet<>();
        EMAModule emaModule = new EMAModule("Модуль учебно-методической деятельности деятельности", plan);
        modules.add(emaModule);

        return modules;
    }

    public Set<OMAModule> getOMAModules() {
        Set<OMAModule> modules = new HashSet<>();
        OMAModule omaModule = new OMAModule("Модуль орагнизационно-методической деятельности", plan);
        modules.add(omaModule);

        return modules;
    }

    public Set<EASection> getEASections() {
        Set<EASection> sections = new HashSet<>();
        EASection section = new EASection("Раздел учебной деятельности", getEATopics());
        sections.add(section);

        return sections;
    }

    public Set<EATopic> getEATopics() {
        Set<EATopic> topics = new HashSet<>();
        EATopic eaTopic = new EATopic("Тема раздела учебной деятельности", plan);
        topics.add(eaTopic);

        return topics;
    }

    public ETPDto getETP_DTO() {
        return new ETPDto(getETP());
    }

    public PlanDto getPlanDTO() {
        return new PlanDto(plan);
    }
}
