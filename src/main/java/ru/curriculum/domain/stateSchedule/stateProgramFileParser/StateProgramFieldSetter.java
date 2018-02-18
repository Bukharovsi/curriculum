package ru.curriculum.domain.stateSchedule.stateProgramFileParser;

import ru.curriculum.domain.stateSchedule.dictionary.IDictionaryValuesFinder;
import ru.curriculum.domain.stateSchedule.entity.Internship;

import java.util.Date;


import static ru.curriculum.domain.stateSchedule.stateProgramFileParser.StateProgramFieldsStorage.*;

public class StateProgramFieldSetter {
    private IDictionaryValuesFinder dictionaryValuesFinder;
    private InternshipParser internshipParser;

    public StateProgramFieldSetter(IDictionaryValuesFinder dictionaryValuesFinder) {
        this.dictionaryValuesFinder = dictionaryValuesFinder;
        this.internshipParser = new InternshipParser();
    }

    public void setFieldToStateProgram(String fieldName, Object value, StateProgramTemplate stateProgram) {
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
            case ADDRESS:
                stateProgram.address(value.toString());
            case RESPONSIBLE_DEPARTMENT:
                stateProgram.responsibleDepartment(dictionaryValuesFinder.findResponsibleDepartment(value.toString()));
            default:
                System.out.println("Неизвестное поле плана гос. горантии");
        }
    }

    public void setFieldToInternship(String fieldName, Object value, Internship internship) {
        if(null == value) {
            return;
        }

        switch (fieldName) {
            case INTERNSHIP:
                addInternshipFields(value.toString(), internship);
                break;
            case INTERNSHIP_THEME:
                internship.theme(value.toString());
                break;
            default:
                System.out.println("Неизвестное поле плана гос гарантий");
        }
    }

    private void addInternshipFields(String value, Internship internship) {
        Date[] date = internshipParser.getStartAndFinishDates(value);
        internship.dateStart(date[0]);
        internship.dateFinish(date[1]);
        String address = internshipParser.getAddress(value);
        internship.address(address);
    }

    private void addLernerCountAndGroupCount(String value, StateProgramTemplate stateProgram) {
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
