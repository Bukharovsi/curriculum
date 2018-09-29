package ru.curriculum.service.etp.controller;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class OrderedRowController implements IRowController {
    private IRowController origin;

    public OrderedRowController(IRowController origin) {
        this.origin = origin;
    }

    public <T extends IRow & Comparable> void add(T newRow, List<T> rows) {
        origin.add(newRow, rows);
        Collections.sort(rows);
    }

    @Override
    public <T extends IRow & Comparable> void remove(Integer rowNumber, List<T> rows) {
        origin.remove(rowNumber, rows);
        Collections.sort(rows);
    }
}
