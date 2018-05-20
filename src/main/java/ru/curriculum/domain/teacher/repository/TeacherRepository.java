package ru.curriculum.domain.teacher.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ru.curriculum.domain.teacher.entity.Teacher;

import java.time.LocalDate;
import java.util.List;

public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Integer> {

    Teacher findByCuratorId(Integer curatorId);

    @Query(value = "" +
            "WITH param AS ( " +
            "    SELECT " +
            "      :theme_name AS theme_name, " +
            "      :etp_id AS etp_id " +
            "), ea_module AS ( " +
            "    SELECT " +
            "      pt.teacher_id, " +
            "      et.name " +
            "    FROM param, education_module em " +
            "      JOIN education_section es ON em.id = es.education_module_id " +
            "      JOIN education_topic et ON es.id = et.education_section_id " +
            "      JOIN etp_plan ep ON et.etp_plan_id = ep.id " +
            "      JOIN plan_teacher pt ON ep.id = pt.plan_id " +
            "    WHERE em.etp_id = param.etp_id AND et.name = param.theme_name " +
            "), ema_module AS ( " +
            "    SELECT " +
            "      pt.teacher_id, " +
            "      emm.name " +
            "    FROM param, education_methodical_module emm " +
            "      JOIN etp_plan ep ON emm.etp_plan_id = ep.id " +
            "      JOIN plan_teacher pt ON ep.id = pt.plan_id " +
            "    WHERE emm.etp_id = param.etp_id AND emm.name = param.theme_name " +
            "), oma_module AS ( " +
            "    SELECT " +
            "      pt.teacher_id, " +
            "      omm.name " +
            "    FROM param, organization_methodical_module omm " +
            "      JOIN etp_plan ep ON omm.etp_plan_id = ep.id " +
            "      JOIN plan_teacher pt ON ep.id = pt.plan_id " +
            "    WHERE omm.etp_id = param.etp_id AND omm.name = param.theme_name " +
            "), teacher_ids AS ( " +
            "  SELECT teacher_id FROM ea_module " +
            "  UNION " +
            "  SELECT teacher_id FROM ema_module " +
            "  UNION " +
            "  SELECT teacher_id FROM oma_module " +
            ") " +
            "SELECT " +
            "  t.* " +
            "FROM teacher t, teacher_ids ti " +
            "WHERE t.id IN (ti.teacher_id) ",
            nativeQuery = true
    )
    List<Teacher> findTeacherDefineInEtpTheme(
            @Param("etp_id") Integer etpId,
            @Param("theme_name") String themeName
    );

    @Query(value = "" +
            "SELECT " +
            "  t.* " +
            "FROM school_day sd " +
            "  JOIN lesson l ON sd.id = l.school_day_id AND sd.timetable_id <> :timetable_id " +
            "  JOIN lesson_teacher lt ON l.id = lt.lesson_id " +
            "  JOIN teacher t ON lt.teacher_id = t.id AND sd.date = :school_date " +
            "  JOIN timetable tt ON tt.id = sd.timetable_id " +
            "  JOIN etp e ON e.id = tt.etp_id AND e.financing_source = 'NOT_BUDGET' ",
            nativeQuery = true
    )
    List<Teacher> findAllHavingLessonOnDate(
            @Param("timetable_id") Integer timetableId,
            @Param("school_date") LocalDate date
    );

    @Query(value = "" +
            "SELECT " +
            "  t.* " +
            "FROM teacher t " +
            "  JOIN lesson_teacher lt ON t.id = lt.teacher_id AND t.id in (:teacher_ids)" +
            "  JOIN lesson l ON lt.lesson_id = l.id AND l.time = :t " +
            "  JOIN school_day sd ON l.school_day_id = sd.id " +
            "    AND sd.timetable_id <> :timetable_id" +
            "    AND sd.date = :d ;",
            nativeQuery = true
    )
    List<Teacher> findAllHavingLessonOnDateAndTime(
            @Param("teacher_ids") List<Integer> teacherId,
            @Param("timetable_id") Integer timetableId,
            @Param("d") LocalDate date,
            @Param("t") String time
    );

}
