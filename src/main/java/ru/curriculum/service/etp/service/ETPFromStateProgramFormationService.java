package ru.curriculum.service.etp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.ETPFactory;
import ru.curriculum.domain.etp.repository.ETPRepository;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;
import ru.curriculum.domain.stateSchedule.repository.StateProgramRepository;
import ru.curriculum.service.etp.dto.ETPDto;
import ru.curriculum.service.etp.exception.ETPAlreadyCreatedFromStateProgramException;

import javax.persistence.EntityNotFoundException;

@Component
public class ETPFromStateProgramFormationService {
    @Autowired
    private StateProgramRepository stateProgramRepository;
    @Autowired
    private ETPRepository etpRepository;
    @Autowired
    private ETPFactory etpFactory;

    public ETPDto formETPTemplate(Integer stateProgramId) {
        if(null != etpRepository.findByStateProgramId(stateProgramId)) {
            throw new ETPAlreadyCreatedFromStateProgramException(stateProgramId);
        }

        StateProgram program = stateProgramRepository.findOne(stateProgramId);
        if(null == program) {
            throw new EntityNotFoundException(
                    String.format("Учебный план с идентификатором %s не найден в системе", stateProgramId));
        }

        ETPDto dto = new ETPDto(etpFactory.makeETPTemplateFromStateProgram(program));
        dto.setStateProgramId(stateProgramId);

        return dto;
    }
}
