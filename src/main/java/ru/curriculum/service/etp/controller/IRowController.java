package ru.curriculum.service.etp.controller;

import java.util.List;

public interface IRowController {

    <T extends IRow & Comparable> void add(T newRow, List<T> rows);

    <T extends IRow & Comparable> void remove(Integer rowNumber, List<T> rows);
}
