package ru.curriculum.service.division;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.curriculum.domain.organization.entity.Division;

@Data
@AllArgsConstructor
public class DivisionDto {

    private Integer id;

    private String name;

    public DivisionDto(Division division) {
        id = division.id();
        name = division.name();
    }

}
