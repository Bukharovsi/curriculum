package ru.curriculum.domain.admin.domain.stateSchedule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.curriculum.domain.stateSchedule.stateProgramFileParser.StateProgramFieldsStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StateProgramFieldsStorageTest {
    private StateProgramFieldsStorage stateProgramFieldsStorage;

    @Before
    public void setUp() {
        stateProgramFieldsStorage = new StateProgramFieldsStorage();
    }

    @Test
    public void findStateProgramFieldsByColumnName_mustFindAllOccurrences() {
        String[] columnNames = {
                "Целевая аудитория (категория слушателей)",
                "Название дополнительной профессиональной программы",
                "Форма обучения (очная, заочная, очно-заочная)",
                "Форма реализация (модульная, интегрированная, корпоративная, командная)",
                "Кол-во слушателей, всего чел./групп",
                "Объем на одного слушателя, в часах",
                "Сроки обучения",
                "Первая помощь (примерная дата)",
                "Стажировка, дата",
                "Тема стажировки",
                "Ответственное структурное подразделение\n",
                "Куратор учебной группы, контактный телефон",
                "Место \n проведения (адрес проведения занятий может быть изменен, о чем слушатели будут информированы дополнительно)"
        };
        int countFieldsMustBeFound = 10;

        List<String> foundedFields = new ArrayList<>();

        for (String name : columnNames) {
            Optional<String> field = stateProgramFieldsStorage.getFieldIfPresent(name);
            if(field.isPresent()) {
                foundedFields.add(field.get());
            }
        }

        Assert.assertEquals(countFieldsMustBeFound, foundedFields.size());
    }
}
