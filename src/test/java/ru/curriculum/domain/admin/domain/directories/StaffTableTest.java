package ru.curriculum.domain.admin.domain.directories;

import org.junit.Assert;
import org.junit.Test;
import ru.curriculum.domain.directories.staffTable.StaffPosition;

public class StaffTableTest {

    @Test
    public void createStaffTable_mustBeCreateCorrectly() {
        StaffPosition staffPosition = new StaffPosition("Занимаемая должность");

        Assert.assertEquals("Занимаемая должность", staffPosition.positionHeld());
        Assert.assertNull(staffPosition.id());
    }
}
