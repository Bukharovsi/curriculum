package ru.curriculum.domain.admin.domain.directories;

import org.junit.Assert;
import org.junit.Test;
import ru.curriculum.domain.directories.staffTable.StaffTable;

public class StaffTableTest extends Assert{

    @Test
    public void createStaffTable_mustBeCreateCorrectly() {
        StaffTable staffTable = new StaffTable("Занимаемая должность");

        assertEquals("Занимаемая должность", staffTable.positionHeld());
        assertNull(staffTable.id());
    }
}
