package ru.curriculum.domain.etp;

import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.financingSource.FinancingSource;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;

@Component
public class ETPFactory {

    public ETP makeETPTemplateFromStateProgram(StateProgram stateProgram) {
        ETP etp = new ETP();
        etp.title(stateProgram.name());
        etp.financingSource(FinancingSource.BUDGET);
        etp.fullTimeLearningBeginDate(stateProgram.dateStart());
        etp.fullTimeLearningEndDate(stateProgram.dateFinish());

        return etp;
    }
}
