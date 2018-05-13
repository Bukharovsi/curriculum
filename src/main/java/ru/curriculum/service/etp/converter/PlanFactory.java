package ru.curriculum.service.etp.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.service.etp.dto.PlanDto;

import java.util.HashSet;
import java.util.Set;

@Component
public class PlanFactory {
    @Autowired
    private TeacherRepository teacherRepository;

    public Plan create(PlanDto planDto) {
        Set<Teacher> teachers = new HashSet<>();
        if(planDto.hasTeacher()) {
            for (Teacher teacher : teacherRepository.findAll(planDto.getTeacherIds())) {
                teachers.add(teacher);
            }
        }

        return Plan.builder()
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
                .teachers(teachers)
                .build();
    }
}
