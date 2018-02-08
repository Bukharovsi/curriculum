package ru.curriculum.service.stateSchedule.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StateProgramFieldComparator {

    private final Set<String> stateProgramFileds =
            new HashSet<String>() {
                { add("Целевая аудитория (категория слушателей)");}
                { add("Название дополнительной профессиональной программы");}
                { add("Форма обучения (очная, заочная, очно-заочная)");}
                { add("Форма реализация (модульная, интегрированная, корпоративная, командная)");}
                { add("Кол-во слушателей, всего чел./групп");}
                { add("Объем на одного слушателя, в часах");}
                { add("Сроки обучения");}
                { add("Первая помощь (примерная дата)");}
                { add("Стажировк, дата");}
                { add("Тема стажировки");}
                { add("Ответственное структурное подразделение");}
                { add("Куратор учебной группы, контактный телефон");}
            };

    private final Set<String> month =
            new HashSet<String>() {
                { add("Январь"); }
                { add("Февраль"); }
                { add("Март"); }
                { add("Апрель"); }
            };

    public boolean isStateProgramField(String text) {
        return stateProgramFileds.contains(text);
    }

    public boolean isMonth(String month) {
        return month.contains(month);
    }

}
