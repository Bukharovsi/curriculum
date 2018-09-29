package ru.curriculum.service.etp.controller;

import java.util.List;

public class RowController implements IRowController {

    @Override
    public <T extends IRow & Comparable> void add(T newRow, List<T> rows) {
        if (newRow.getNumber() <= rows.size()) {
            shiftRows(newRow.getNumber(), rows, Direction.DOWN);
        }
        rows.add(newRow);
    }

    @Override
    public <T extends IRow & Comparable> void  remove(Integer number, List<T> rows) {
        rows.removeIf(r -> number.equals(r.getNumber()));
        shiftRows(number, rows, Direction.UP);
    }

    private void shiftRows(Integer number, List<? extends IRow> rows, Direction direction) {
        for (IRow row : rows) {
            if (row.getNumber() >= number) {
                row.setNumber(row.getNumber() + direction.value());
            }
        }
    }

    enum Direction {
        UP(-1),
        DOWN(1);

        private int direction;

        Direction(int direction) {
            this.direction = direction;
        }

        public int value() {
            return direction;
        }
    }
}
