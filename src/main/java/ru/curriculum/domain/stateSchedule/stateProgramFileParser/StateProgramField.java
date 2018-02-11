package ru.curriculum.domain.stateSchedule.stateProgramFileParser;

import ru.curriculum.domain.stateSchedule.dictionary.IDictionaryValuesFinder;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;

import static ru.curriculum.domain.stateSchedule.stateProgramFileParser.StateProgramFieldComparator.*;

public class StateProgramField {
    private IDictionaryValuesFinder dictionaryValuesFinder;

    public StateProgramField(IDictionaryValuesFinder dictionaryValuesFinder) {
        this.dictionaryValuesFinder = dictionaryValuesFinder;
    }

    public void addFieldToStateProgram(String fieldName, Object value, StateProgram stateProgram) {
        if (null == value) {
            return;
        }

        switch (fieldName) {
            case LERNER_COUNT:
                addLernerCountAndGroupCount(value.toString(), stateProgram);
                break;
            case NAME:
                stateProgram.name(value.toString());
                break;
            case TARGET_AUDIENCE:
                stateProgram.targetAudience(value.toString());
                break;
            case COUNT_OF_HOURS_PER_LERNER:
                stateProgram.countOfHoursPerLerner(toInt(value.toString()));
                break;
            case CURATOR:
                stateProgram.curator(dictionaryValuesFinder.findCurator(value.toString()));
                break;
            case MODE:
                stateProgram.mode(dictionaryValuesFinder.findMode(value.toString()));
            case IMPLEMENTATION_FORM:
                stateProgram.implementationForm(dictionaryValuesFinder.findImplementationForm(value.toString()));
            default:
                System.out.println("Неизвестное поле плана гос. горантии");
        }
    }

    private void addLernerCountAndGroupCount(String value, StateProgram stateProgram) {
        if(value.contains("/")) {
            String[] str = value.split("/");
            if(2 == str.length) {
                stateProgram.lernerCount(toInt(str[0]));
                stateProgram.groupCount(toInt(str[1]));
            } else if(1 == str.length) {
                stateProgram.lernerCount(toInt(str[0]));
            }
        } else {
            stateProgram.lernerCount(toInt(value));
        }
    }

    private Integer toInt(String string) {
        return string.trim().matches("[0-9]+") ? Integer.valueOf(string.trim()) : null;
    }
}
