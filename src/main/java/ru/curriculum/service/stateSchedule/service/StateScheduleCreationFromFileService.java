package ru.curriculum.service.stateSchedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;
import ru.curriculum.domain.stateSchedule.repository.StateProgramRepository;
import ru.curriculum.domain.stateSchedule.stateProgramFileParser.StateProgramFileParser;
import ru.curriculum.domain.stateSchedule.stateProgramFileParser.StateProgramTemplateToStateProgramConverter;

import java.util.List;

@Component
public class StateScheduleCreationFromFileService {
    @Autowired
    private StateProgramFileParser stateProgramFileParser;
    @Autowired
    private StateProgramRepository stateProgramRepository;
    @Autowired
    private StateProgramTemplateToStateProgramConverter stateProgramTemplateToStateProgramConverter;

    public FileParseResult makeStateScheduleTemplatesFromFile(MultipartFile multipartFile) {
        try {
            FileParseResult parseResult = stateProgramFileParser.parse(multipartFile);
            if(parseResult.parseIsSuccess()) {
                List<StateProgram> programs = stateProgramTemplateToStateProgramConverter.convert(parseResult.getStateProgramTemplates());
                stateProgramRepository.save(programs);
            }
            return parseResult;
        } catch (Exception e) {
            throw new RuntimeException(String.format("При обработке файла произошли ошбики. %s", e.getMessage()));
        }
    }
}
