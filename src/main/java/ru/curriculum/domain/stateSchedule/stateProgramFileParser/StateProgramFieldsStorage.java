package ru.curriculum.domain.stateSchedule.stateProgramFileParser;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class StateProgramFieldsStorage {
    final static String TARGET_AUDIENCE = "целеваяаудитория";
    final static String NAME = "названиедополнительнойпрофессиональнойпрограммы";
    final static String MODE = "формаобучения";
    final static String IMPLEMENTATION_FORM = "формареализация"; // TODO: реализация или реализации
    final static String LERNER_COUNT = "колвослушателей";
    final static String COUNT_OF_HOURS_PER_LERNER = "объемнаодногослушателя";
    final static String CURATOR = "кураторучебнойгруппы";
    final static String ADDRESS = "местопроведения";
    final static String INTERNSHIP = "стажиров";
    final static String INTERNSHIP_THEME = "темастажировки";
    final static String RESPONSIBLE_DEPARTMENT = "ответственноеподразделение";

    private final Set<String> stateProgramFieldsTemplate =
            new HashSet<String>() {
                { add(TARGET_AUDIENCE);}
                { add(NAME);}
                { add(MODE);}
                { add(IMPLEMENTATION_FORM);}
                { add(LERNER_COUNT);}
                { add(COUNT_OF_HOURS_PER_LERNER);}
                { add(CURATOR);}
                { add(ADDRESS);}
                { add(INTERNSHIP);}
                { add(INTERNSHIP_THEME);}
                { add(RESPONSIBLE_DEPARTMENT);}
            };

    public Optional<String> getFieldIfPresent(String columnName) {
        for (String template : stateProgramFieldsTemplate) {
            if(toSearchTemplate(columnName).contains(template)) {
                return Optional.of(template);
            }
        }
        return Optional.empty();
    }

    boolean isInternshipField(String columnName) {
        String searchTemplate = toSearchTemplate(columnName);
        return searchTemplate.contains(INTERNSHIP) || searchTemplate.contains(INTERNSHIP);
    }

    private String toSearchTemplate(String fieldName) {
        return fieldName.replaceAll("[^а-яА-Я]", "").trim().toLowerCase();
    }
}
