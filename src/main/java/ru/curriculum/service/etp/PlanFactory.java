package ru.curriculum.service.etp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.service.etp.dto.PlanDTO;

@Component
public class PlanFactory {
    @Autowired
    private TeacherRepository teacherRepository;

    public Plan create(PlanDTO planDTO) {
        Teacher teacher = null;
        if(null != planDTO.getTeacherId()) {
            teacher = teacherRepository.findOne(planDTO.getTeacherId());
        }
        Plan plan = Plan.builder()
                .id(planDTO.getId())
                .lectures(planDTO.getLectures())
                .practices(planDTO.getPractices())
                .independentWorks(planDTO.getIndependentWorks())
                .consultations(planDTO.getConsultations())
                .peerReviews(planDTO.getPeerReviews())
                .credits(planDTO.getCredits())
                .others(planDTO.getOthers())
                .standard(planDTO.getStandard())
                .totalHours(planDTO.getTotalHours())
                .lernerCount(planDTO.getLernerCount())
                .groupCount(planDTO.getGroupCount())
                .conditionalPagesCount(planDTO.getConditionalPagesCount())
                .teacher(teacher)
                .build();

        return plan;
    }
}
