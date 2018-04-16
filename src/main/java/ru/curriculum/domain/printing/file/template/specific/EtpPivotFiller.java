package ru.curriculum.domain.printing.file.template.specific;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import ru.curriculum.service.etp.dto.*;

public class EtpPivotFiller extends EtpFiller {

    private final ETPDto etp;
    private final ETPTemplateCoordinates tsr;

    public EtpPivotFiller(ETPDto etp, ETPTemplateCoordinates tsr, DefaultCellStyle defaultCellStyle) {
        super(defaultCellStyle);
        this.etp = etp;
        this.tsr = tsr;
    }


    public void fillPivotCell(Sheet sheet, Integer eaModuleLastRow) {
        fillTableCellPlansValue(sheet.getRow(eaModuleLastRow), etp.getEmaModuleTotalRow());
        fillTableCellPlansValue(sheet.getRow(eaModuleLastRow + 1), etp.getOmaModuleTotalRow());
        fillTableCellPlansValue(sheet.getRow(eaModuleLastRow + 2), etp.getEaModuleTotalRow());
        fillTableCellPlansValue(sheet.getRow(eaModuleLastRow + 3), etp.getEtpTotalRow());
    }


    private void fillTableCellPlansValue(Row row, TotalRow totalRow) {
        createTableCell(row, tsr.lectures(), totalRow.getLectures());
        createTableCell(row, tsr.practices(), totalRow.getPractices());
        createTableCell(row, tsr.independentWorks(), totalRow.getIndependentWorks());
        createTableCell(row, tsr.consultations(), totalRow.getConsultations());
        createTableCell(row, tsr.peerReviews(), totalRow.getPeerReviews());
        createTableCell(row, tsr.credits(), totalRow.getCredits());
        createTableCell(row, tsr.others(), totalRow.getOthers());
        createTableCell(row, tsr.standard(), totalRow.getStandard());
        createTableCell(row, tsr.totalHours(), totalRow.getTotalHours());
        createTableCell(row, tsr.lernerCount(), totalRow.getLernerCount());
        createTableCell(row, tsr.groupCount(), totalRow.getGroupCount());
        createTableCell(row, tsr.conditionalPagesCount(), totalRow.getConditionalPagesCount());
    }
}
