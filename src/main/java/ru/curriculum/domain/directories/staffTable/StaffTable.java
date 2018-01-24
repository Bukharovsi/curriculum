package ru.curriculum.domain.directories.staffTable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "staff_table")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor
@EqualsAndHashCode
public class StaffTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String positionHeld;

    public StaffTable(String positionHeld) {
        this.positionHeld = positionHeld;
    }
}
