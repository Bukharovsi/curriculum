package ru.curriculum.service.stateSchedule.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.domain.organization.entity.Division;
import ru.curriculum.domain.organization.repository.DivisionRepository;
import ru.curriculum.domain.stateSchedule.entity.ImplementationForm;
import ru.curriculum.domain.stateSchedule.entity.Internship;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;
import ru.curriculum.domain.stateSchedule.entity.StudyMode;
import ru.curriculum.domain.stateSchedule.repository.ImplementationFormRepository;
import ru.curriculum.domain.stateSchedule.repository.StudyModeRepository;
import ru.curriculum.service.stateSchedule.dto.InternshipDto;
import ru.curriculum.service.stateSchedule.dto.StateProgramCreationDto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class DtoToStateScheduleConverter {

    @Autowired
    private CuratorRepository curatorRepository;

    @Autowired
    private StudyModeRepository studyModeRepository;

    @Autowired
    private ImplementationFormRepository implFormRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    public StateProgram createBasedOnDto(StateProgramCreationDto stateProgramDto) {

        Curator curator = curatorRepository.findOne(stateProgramDto.getCuratorId());
        ImplementationForm implementForm = implFormRepository.findOne(stateProgramDto.getImplementationFormId());
        StudyMode studyMode = studyModeRepository.findOne(stateProgramDto.getModeId());
        Division responsibleDepartment = divisionRepository.findOne(stateProgramDto.getResponsibleDepartmentId());

        StateProgram newStateProgram = StateProgram.builder()
                .id(stateProgramDto.getId())
                .name(stateProgramDto.getName())
                .curator(curator)
                .mode(studyMode)
                .implementationForm(implementForm)
                .internships(internshipsDtoToInternshipEntity(stateProgramDto.getInternships()))
                .dateStart(stateProgramDto.getDateStart())
                .dateFinish(stateProgramDto.getDateFinish())
                .lernerCount(stateProgramDto.getLernerCount())
                .groupCount(stateProgramDto.getGroupCount())
                .countOfHoursPerLerner(stateProgramDto.getCountOfHoursPerLerner())
                .responsibleDepartment(responsibleDepartment)
                .address(stateProgramDto.getAddress())
                .targetAudience(stateProgramDto.getTargetAudience())
                .build();

        return newStateProgram;
    }

    private Set<Internship> internshipsDtoToInternshipEntity(Collection<InternshipDto> internshipDtos) {
        Set<Internship> internships = new HashSet<>();
        internshipDtos.forEach(dto -> internships.add(
                Internship.builder()
                        .id(dto.getId())
                        .dateStart(dto.getDateStart())
                        .dateFinish(dto.getDateFinish())
                        .theme(dto.getTheme()).address(dto.getAddress())
                        .build()
                )
        );

        return internships;
    }
}
