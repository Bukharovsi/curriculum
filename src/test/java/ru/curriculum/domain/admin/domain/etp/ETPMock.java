package ru.curriculum.domain.admin.domain.etp;

import ru.curriculum.domain.directories.academicDegree.AcademicDegree;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.domain.etp.entity.VolumeInHours;
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
import ru.curriculum.service.etp.statusManager.ETPStatus;

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
                null,
                "Учебный план",
                "Научить",
                new Date(5),
                new Date(6),
                new Date(1),
                new Date(2),
                new Date(3),
                new Date(4),
                ETPStatus.DRAFT,
                50,
                70,
                FinancingSource.BUDGET,
                getVolumeInHours(),
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
                1.6,
                3.0,
                getTeachers()
        );
    }

    public VolumeInHours getVolumeInHours() {
        return VolumeInHours.builder()
                .total(11.0)
                .perOneListener(1.0)
                .fullTimePerOneListener(2.0)
                .distancePerOneListener(3.0)
                .totalStudyWork(4.0)
                .fullTimeStudyWork(5.0)
                .distanceStudyWork(null)
                .paymentStudyWork(7.0)
                .fullTimePaymentForStudyWork(8.0)
                .distancePaymentForStudyWork(9.0)
                .emaPaymentForStudyWork(10.0)
                .omaPaymentForStudyWork(11.0)
                .inLoad(12.0)
                .fullTimeInLoad(1.0)
                .distanceInLoad(1.0)
                .emaInLoad(12.0)
                .omaInLoad(13.0)
                .build();
    }

    public void applyNewPlanForModules(Plan plan) {
        this.plan = plan;
    }

    public Set<Teacher> getTeachers() {
        Set<Teacher> teachers = new HashSet<>();
        teachers.add(getTeacher());
        return teachers;
    }

    public Teacher getTeacher() {
        return new Teacher(
                1,
                "Иванов",
                "Иван",
                "Иванович",
                new AcademicDegree("ph_d", "Доктор наук"),
                "Ирорт",
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

    public ETPDto getETPDto() {
        return new ETPDto(getETP());
    }

    public PlanDto getPlanDTO() {
        return new PlanDto(plan);
    }
}
