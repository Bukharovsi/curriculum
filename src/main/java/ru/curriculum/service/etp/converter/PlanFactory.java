package ru.curriculum.service.etp.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.service.etp.dto.PlanDto;

@Component
public class PlanFactory {
    @Autowired
    private TeacherRepository teacherRepository;

    public Plan create(PlanDto planDto) {
        Teacher teacher = null;
        if(null != planDto.getTeacherId()) {
            teacher = teacherRepository.findOne(planDto.getTeacherId());
        }
        Plan plan = Plan.builder()
                .id(planDto.getId())
                .lectures(planDto.getLectures())
                .practices(planDto.getPractices())
                .independentWorks(planDto.getIndependentWorks())
                .consultations(planDto.getConsultations())
                .peerReviews(planDto.getPeerReviews())
                .credits(planDto.getCredits())
                .others(planDto.getOthers())
                .standard(planDto.getStandard())
                .hoursPerOneListener(planDto.getHoursPerOneListener())
                .totalHours(planDto.getTotalHours())
                .lernerCount(planDto.getLernerCount())
                .groupCount(planDto.getGroupCount())
                .conditionalPagesCount(planDto.getConditionalPagesCount())
                .teacher(teacher)
                .build();

        return plan;
    }
}
