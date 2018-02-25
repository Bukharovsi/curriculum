package ru.curriculum.domain.stateSchedule.stateProgramFileParser;

import java.util.HashMap;
import java.util.Map;

public class ColumnIndexToFieldMap {
    private Map<Integer, String> map;

    public ColumnIndexToFieldMap() {
        this.map = new HashMap<>();
    }

    public void put(Integer columnIndex, String value) {
        map.put(columnIndex, value);
    }

    public boolean columnIsPresent(Integer columnIndex) {
        return map.containsKey(columnIndex);
    }

    public String getField(Integer index) {
        return map.get(index);
    }
}
