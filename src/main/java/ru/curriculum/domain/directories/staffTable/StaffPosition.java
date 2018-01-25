package ru.curriculum.domain.directories.staffTable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "staff_position")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor
@EqualsAndHashCode
public class StaffPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String positionHeld;

    public StaffPosition(String positionHeld) {
        this.positionHeld = positionHeld;
    }
}
