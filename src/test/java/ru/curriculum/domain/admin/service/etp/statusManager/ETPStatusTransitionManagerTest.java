package ru.curriculum.domain.admin.service.etp.statusManager;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.curriculum.service.etp.statusManager.ETPStatus;
import ru.curriculum.service.etp.statusManager.ETPStatusTransitionManager;

import java.util.ArrayList;
import java.util.List;

import static ru.curriculum.service.etp.statusManager.ETPStatus.*;

@RunWith(DataProviderRunner.class)
public class ETPStatusTransitionManagerTest {
    private ETPStatusTransitionManager etpStatusTransitionManager;

    @Before
    public void setUp() {
        etpStatusTransitionManager = new ETPStatusTransitionManager();
    }

    @Test
    @UseDataProvider("availableStatuses")
    public void getAvailableStatuses(ETPStatus sourceStatus, List<ETPStatus> availableStatuses) {
        List<ETPStatus> statuses = etpStatusTransitionManager.getAvailableStatuses(sourceStatus);

        Assert.assertTrue(availableStatuses.containsAll(statuses));
    }

    @DataProvider
    public static Object[][] availableStatuses() {
        return new Object[][] {
                { DRAFT, new ArrayList<ETPStatus>() {{ add(CONSIDERATION); }} },
                { CONSIDERATION, new ArrayList<ETPStatus>() {{ add(STATEMENT); add(REVISION); }} },
                { REVISION, new ArrayList<ETPStatus>() {{ add(CONSIDERATION); }} },
                { STATEMENT, new ArrayList<ETPStatus>() {{ add(STATEMENT); }} }
        };
    }

}
