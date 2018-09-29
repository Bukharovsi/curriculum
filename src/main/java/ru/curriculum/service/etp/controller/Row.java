package ru.curriculum.service.etp.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Row implements IRow, Comparable {
    public Integer number;

    public Row(Integer number) {
        this.number = number;
    }

    @Override
    public int compareTo(Object o) {
        Row other = (Row) o;
        return (this.number < other.number) ? -1 : ((this.number.equals(other.number)) ? 0 : 1);
    }
}
