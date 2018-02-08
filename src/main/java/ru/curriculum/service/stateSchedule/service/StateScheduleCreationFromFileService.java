package ru.curriculum.service.stateSchedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class StateScheduleCreationFromFileService {

    private IStateProgramFileParser stateProgramFileParser;

    public void makeStateScheduleTemplatesFromFile(MultipartFile file) {
        /**
         *
         * if file has correct ext:
         *      then begin parse file
         * else
         *      return invalid file ext error
         *
         */
    }
}
