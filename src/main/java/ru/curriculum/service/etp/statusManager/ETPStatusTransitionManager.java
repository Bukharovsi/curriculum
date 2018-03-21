package ru.curriculum.service.etp.statusManager;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.curriculum.service.etp.statusManager.ETPStatus.*;

@Component
public class ETPStatusTransitionManager {
    private final Map<ETPStatus, List<ETPStatus>> transitions = new HashMap<ETPStatus, List<ETPStatus>>() {{
        put(DRAFT, new ArrayList<ETPStatus>(){{ add(CONSIDERATION); }});
        put(CONSIDERATION, new ArrayList<ETPStatus>(){{ add(REVISION); add(STATEMENT); }});
        put(REVISION, new ArrayList<ETPStatus>(){{ add(CONSIDERATION); }});
        put(STATEMENT, new ArrayList<>());
    }};

    public boolean transitionIsAvailable(ETPStatus source, ETPStatus target) {
        return transitions.get(source).contains(target);
    }

    public List<ETPStatus> getAvailableStatuses(ETPStatus status) {
        List<ETPStatus> statuses = new ArrayList<>();
        statuses.addAll(transitions.get(status));

        return statuses;
    }

    public List<ETPStatus> getAvailableStatusesForInitial() {
        return new ArrayList<ETPStatus>(){{add(ETPStatus.DRAFT);}};
    }
}
