package ru.curriculum.domain.printing.file.template.specific;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class CellAddress {
    private Integer rowNumber;
    private Integer cellNumber;
}
