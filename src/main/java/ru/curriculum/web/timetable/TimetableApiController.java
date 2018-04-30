package ru.curriculum.web.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.curriculum.service.timetable.TimetableSearchService;
import ru.curriculum.service.timetable.dto.TeacherDto;


@RestController
@RequestMapping(path = "/api/timetable")
public class TimetableApiController {

    @Autowired
    private TimetableSearchService timetableSearchService;

    @RequestMapping(value = "/teacherByTheme", method = RequestMethod.GET)
    public ResponseEntity getTeacherByTimetableTheme(
            @RequestParam("etp_id") Integer eptId,
            @RequestParam("theme") String themeName
    ) {
        TeacherDto dto = timetableSearchService.getTeacherDefineInEtpTheme(eptId, themeName);
        if(null == dto) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
}
