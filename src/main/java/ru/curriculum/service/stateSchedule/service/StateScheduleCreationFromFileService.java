package ru.curriculum.service.stateSchedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;
import ru.curriculum.domain.stateSchedule.repository.StateProgramRepository;
import ru.curriculum.domain.stateSchedule.stateProgramFileParser.StateProgramFileParser;
import ru.curriculum.service.stateSchedule.converter.MultipartFileToFileConverter;

import java.util.List;

@Component
public class StateScheduleCreationFromFileService {
    @Autowired
    private StateProgramFileParser stateProgramFileParser;
    @Autowired
    private MultipartFileToFileConverter multipartFileToFileConverter;
    @Autowired
    private StateProgramRepository stateProgramRepository;

    public void makeStateScheduleTemplatesFromFile(MultipartFile multipartFile) {
        try {
            List<StateProgram> statePrograms = stateProgramFileParser.parse(multipartFile);
            stateProgramRepository.save(statePrograms);
        } catch (Exception e) {
            throw new RuntimeException(String.format("При обработке файла произошли ошбики. %s", e.getMessage()));
        }
    }
}
