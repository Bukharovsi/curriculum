package ru.curriculum.domain.printing.file.template.specific;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import ru.curriculum.domain.printing.file.excel.DefaultCellStyle;
import ru.curriculum.service.etp.dto.EMAModuleDto;
import ru.curriculum.service.etp.dto.ETPDto;
import ru.curriculum.service.etp.dto.PlanDto;

public class EtpEmaFiller extends EtpFiller {

    private final ETPDto etp;
    private final ETPTemplateCoordinates tsr;

    public EtpEmaFiller(ETPDto etp, ETPTemplateCoordinates tsr, DefaultCellStyle defaultCellStyle) {
        super(defaultCellStyle);
        this.etp = etp;
        this.tsr = tsr;
    }

    public Integer fillEmaModule(Sheet sheet) {
        sheet.shiftRows(
            tsr.tableStartRow(),
            tsr.tableStartRow() + tsr.offset(),
            etp.getEmaModules().size()
        );

        int index = tsr.tableStartRow();
        int rowNumber = tsr.tableStartRow() + etp.getEmaModules().size();
        int moduleIndex = 1;

        for (EMAModuleDto module : etp.getEmaModules()) {
            Row row = sheet.getRow(index);
            createSubmoduleTableCell(row, 0, String.format("%s.", moduleIndex));
            createSubmoduleTableCell(row, tsr.topicName(), module.getName());
            fillTableCellPlansValue(row, module.getPlan());
            moduleIndex++;
            index++;
        }

        return rowNumber + 1;
    }


    private void fillTableCellPlansValue(Row row, PlanDto plan) {
        createTableCell(row, tsr.lectures(), plan.getLectures());
        createTableCell(row, tsr.practices(), plan.getPractices());
        createTableCell(row, tsr.independentWorks(), plan.getIndependentWorks());
        createTableCell(row, tsr.consultations(), plan.getConsultations());
        createTableCell(row, tsr.peerReviews(), plan.getPeerReviews());
        createTableCell(row, tsr.credits(), plan.getCredits());
        createTableCell(row, tsr.others(), plan.getOthers());
        createTableCell(row, tsr.standard(), plan.getStandard());
        createTableCell(row, tsr.totalHours(), plan.getTotalHours());
        createTableCell(row, tsr.lernerCount(), plan.getLernerCount());
        createTableCell(row, tsr.groupCount(), plan.getGroupCount());
        createTableCell(row, tsr.conditionalPagesCount(), plan.getConditionalPagesCount());
        String teacher = plan.hasTeacher() ? plan.getTeacher().getFullName() : "";
        createTableCell(row, tsr.teacher(), teacher);
    }


}
