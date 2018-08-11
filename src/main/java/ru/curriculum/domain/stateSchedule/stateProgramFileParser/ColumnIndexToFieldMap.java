package ru.curriculum.domain.stateSchedule.stateProgramFileParser;

import java.util.HashMap;
import java.util.Map;

class ColumnIndexToFieldMap {
    private Map<Integer, String> map;

    ColumnIndexToFieldMap() {
        this.map = new HashMap<>();
    }

    void put(Integer columnIndex, String value) {
        map.put(columnIndex, value);
    }

    boolean columnIsPresent(Integer columnIndex) {
        return map.containsKey(columnIndex);
    }

    String getField(Integer index) {
        return map.get(index);
    }
}
