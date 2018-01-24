package ru.curriculum.domain.admin.service.directories;

import org.junit.Assert;
import org.junit.Test;
import ru.curriculum.domain.directories.staffTable.StaffTable;
import ru.curriculum.service.directories.staffTable.StaffTableDto;

public class StaffTableDtoTest extends Assert {

    @Test
    public void createStaffTableDto_mustBeCreateCorrectly() {
        StaffTableDto dto = new StaffTableDto(1, "Занимаемая должность");

        assertEquals("Занимаемая должность", dto.getPositionHeld());
        assertEquals(new Integer(1), dto.getId());
    }
}
