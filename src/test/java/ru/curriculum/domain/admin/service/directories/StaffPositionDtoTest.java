package ru.curriculum.domain.admin.service.directories;

import org.junit.Assert;
import org.junit.Test;
import ru.curriculum.service.directories.staffTable.StaffPositionDto;

public class StaffPositionDtoTest extends Assert {

    @Test
    public void createStaffTableDto_mustBeCreateCorrectly() {
        StaffPositionDto dto = new StaffPositionDto(1, "Занимаемая должность");

        assertEquals("Занимаемая должность", dto.getPositionHeld());
        assertEquals(new Integer(1), dto.getId());
    }
}
