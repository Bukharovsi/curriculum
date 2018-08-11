package ru.curriculum.domain.stateSchedule.stateProgramFileParser;

import org.springframework.stereotype.Component;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class StateProgramTemplateToStateProgramConverter {
    public List<StateProgram> convert(List<StateProgramTemplate> templates) {
        List<StateProgram> statePrograms = new ArrayList<>();
        for (StateProgramTemplate template : templates) {
            StateProgram program = StateProgram.builder()
                    .targetAudience(template.targetAudience())
                    .name(template.name())
                    .dateStart(template.dateStart())
                    .dateFinish(template.dateFinish())
                    .groupCount(template.groupCount())
                    .lernerCount(template.lernerCount())
                    .countOfHoursPerLerner(template.countOfHoursPerLerner())
                    .curator(template.curator())
                    .address(template.address())
                    .mode(template.mode())
                    .implementationForm(template.implementationForm())
                    .internships(new HashSet<>(template.internships()))
                    .build();
            statePrograms.add(program);
        }
        return statePrograms;
    }
}
