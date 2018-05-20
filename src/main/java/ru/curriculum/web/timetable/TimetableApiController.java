package ru.curriculum.web.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.curriculum.service.timetable.TimetableSearchService;


@PreAuthorize("permitAll()")
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
        return ResponseEntity.ok(
                timetableSearchService.getTeacherDefineInEtpTheme(eptId, themeName)
        );
    }
}
