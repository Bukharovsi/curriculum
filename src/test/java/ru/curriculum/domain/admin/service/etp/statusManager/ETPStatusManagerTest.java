package ru.curriculum.domain.admin.service.etp.statusManager;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.service.etp.statusManager.ETPStatus;
import ru.curriculum.service.etp.statusManager.ETPStatusManager;
import ru.curriculum.service.etp.statusManager.ETPStatusTransitionManager;
import ru.curriculum.service.etp.statusManager.IllegalTransitionException;

import java.util.ArrayList;
import java.util.List;

import static ru.curriculum.service.etp.statusManager.ETPStatus.*;

@RunWith(DataProviderRunner.class)
public class ETPStatusManagerTest {
    private ETPStatusManager manager;

    @Before
    public void setUp() {
        manager = new ETPStatusManager(new ETPStatusTransitionManager());
    }

    @Test
    @UseDataProvider("statusesTransitionBetweenAreDefined")
    public void moveEtpToNewStatus_mustMoveCorrectly(ETP etp, ETPStatus newStatus, ETPStatus etpNewStatus) {
        manager.moveEtpToNewStatus(etp, newStatus);

        Assert.assertEquals(etp.status(), etpNewStatus);
    }

    @Test(expected = IllegalTransitionException.class)
    @UseDataProvider("statusesTransitionBetweenAreNotDefined")
    public void tryToMoveEtpToNewStatusTransitionBetweenAreNotDefined_mustThrowException(ETP etp, ETPStatus newStatus) {
        manager.moveEtpToNewStatus(etp, newStatus);
    }

    @Test
    @UseDataProvider("availableStatuses")
    public void getAvailableStatuses_mustReturnAllAvailableStatuses(ETPStatus sourceStatus, List<ETPStatus> availableStatuses) {
        List<ETPStatus> statuses = manager.getAvailableStatuses(sourceStatus);

        Assert.assertTrue(availableStatuses.containsAll(statuses));
    }

    @Test
    public void getAvailableStatusesForNewEtp_mustReturnEmptyList() {
        List<ETPStatus> statuses = manager.getAvailableStatusesForNewEtp();

        Assert.assertEquals(0, statuses.size());
    }

    @DataProvider
    public static Object[][] statusesTransitionBetweenAreDefined() {
        return new Object[][] {
                { getEtp(DRAFT), CONSIDERATION, CONSIDERATION },
                { getEtp(CONSIDERATION), REVISION, REVISION },
                { getEtp(REVISION), CONSIDERATION, CONSIDERATION },
                { getEtp(CONSIDERATION), STATEMENT, STATEMENT }
        };
    }

    @DataProvider
    public static Object[][] statusesTransitionBetweenAreNotDefined() {
        return new Object[][] {
                { getEtp(DRAFT), STATEMENT },
                { getEtp(DRAFT), REVISION },
                { getEtp(DRAFT), DRAFT },
                { getEtp(REVISION), STATEMENT },
                { getEtp(REVISION), DRAFT },
                { getEtp(REVISION), REVISION },
                { getEtp(CONSIDERATION), DRAFT },
                { getEtp(CONSIDERATION), CONSIDERATION },
                { getEtp(STATEMENT), DRAFT },
                { getEtp(STATEMENT), REVISION },
                { getEtp(STATEMENT), CONSIDERATION },
                { getEtp(STATEMENT), STATEMENT },
        };
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

    private static ETP getEtp(ETPStatus status) {
        ETP etp = new ETP();
        etp.status(status);
        return etp;
    }
}
