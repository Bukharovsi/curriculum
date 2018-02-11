package ru.curriculum.domain.stateSchedule.stateProgramFileParser;


import java.util.HashSet;
import java.util.Set;

public class StateProgramFieldComparator {
    public final static String TARGET_AUDIENCE = "целеваяаудитория(категорияслушателей)";
    public final static String NAME = "названиедополнительнойпрофессиональнойпрограммы";
    public final static String MODE = "формаобучения(очная,заочная,очно-заочная)";
    public final static String IMPLEMENTATION_FORM = "формареализация(модульная,интегрированная,корпоративная,командная)";
    public final static String LERNER_COUNT = "кол-вослушателей,всегочел./групп";
    public final static String COUNT_OF_HOURS_PER_LERNER = "объемнаодногослушателя,вчасах";
    public final static String CURATOR = "кураторучебнойгруппы,контактныйтелефон";

    private final Set<String> stateProgramFields =
            new HashSet<String>() {
                { add(TARGET_AUDIENCE);}
                { add(NAME);}
                { add(MODE);}
                { add(IMPLEMENTATION_FORM);}
                { add(LERNER_COUNT);}
                { add(COUNT_OF_HOURS_PER_LERNER);}
                { add(CURATOR);}
            };

    public boolean isStateProgramField(String text) {
        return stateProgramFields.contains(toFieldFormat(text));
    }

    public String toFieldFormat(String fieldName) {
        return fieldName.replaceAll("\\s+","").toLowerCase();
    }
}
