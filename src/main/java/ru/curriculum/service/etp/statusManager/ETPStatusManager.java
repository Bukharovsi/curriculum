package ru.curriculum.service.etp.statusManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.entity.ETP;

import java.util.List;


@Component
public class ETPStatusManager {
    private ETPStatusTransitionManager etpStatusTransitionManager;

    @Autowired
    public ETPStatusManager(ETPStatusTransitionManager etpStatusTransitionManager) {
        this.etpStatusTransitionManager = etpStatusTransitionManager;
    }

    public void moveEtpToNewStatus(ETP etp, ETPStatus newStatus) {
        if(!etpStatusTransitionManager.transitionIsAvailable(etp.status(), newStatus)) {
            throw new IllegalTransitionException(etp.status(), newStatus);
        }
        etp.status(newStatus);
    }

    public List<ETPStatus> getAvailableStatuses(ETPStatus status) {
        return etpStatusTransitionManager.getAvailableStatuses(status);
    }

    public List<ETPStatus> getAvaliableStatusesForNewEtp() {
        return etpStatusTransitionManager.getAvailableStatusesForInitial();
    }
}
