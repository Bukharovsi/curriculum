package ru.curriculum.domain.admin.domain.directories;

import org.junit.Assert;
import org.junit.Test;
import ru.curriculum.domain.directories.staffTable.StaffPosition;

public class StaffTableTest extends Assert{

    @Test
    public void createStaffTable_mustBeCreateCorrectly() {
        StaffPosition staffPosition = new StaffPosition("Занимаемая должность");

        assertEquals("Занимаемая должность", staffPosition.positionHeld());
        assertNull(staffPosition.id());
    }
}
