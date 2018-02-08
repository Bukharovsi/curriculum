package ru.curriculum.service.stateSchedule.service;

import org.springframework.web.multipart.MultipartFile;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;

import java.io.IOException;

public interface IStateProgramFileParser {

    StateProgram parse(MultipartFile multipartFile) throws IOException;
}
