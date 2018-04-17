package ru.curriculum.domain.printing.file.template.specific;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import ru.curriculum.service.etp.dto.ETPDto;

import java.text.SimpleDateFormat;

public class EtpHeaderFiller {

    private final ETPDto etp;
    private final ETPTemplateCoordinates tsr;
    private final DefaultCellStyle defaultCellStyle;

    public EtpHeaderFiller(ETPDto etp, ETPTemplateCoordinates tsr, DefaultCellStyle defaultCellStyle) {
        this.etp = etp;
        this.tsr = tsr;
        this.defaultCellStyle = defaultCellStyle;
    }

    public void fillHeaders(Sheet sheet) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        fillHeaderCell(sheet, tsr.etpTheme(), etp.getTitle());
        fillHeaderCell(sheet, tsr.etpTarget(), etp.getTarget());
        fillHeaderCell(sheet, tsr.etpDistanceBeginDate(), dateFormat.format(etp.getDistanceLearningBeginDate()));
        fillHeaderCell(sheet, tsr.etpDistanceEndDate(), dateFormat.format(etp.getDistanceLearningEndDate()));
        fillHeaderCell(sheet, tsr.etpFullTimeBeginDate(), dateFormat.format(etp.getFullTimeLearningBeginDate()));
        fillHeaderCell(sheet, tsr.etpFullTimeEndDate(), dateFormat.format(etp.getFullTimeLearningEndDate()));
        fillHeaderCell(sheet, tsr.etpSchoolDays(), etp.getSchoolDaysCount());
        fillHeaderCell(sheet, tsr.etpLernerCount(), etp.getLernerCount());
        fillHeaderCell(sheet, tsr.etpFinancingSource(), etp.getFinancingSource().getDisplayName());
    }

    private void fillHeaderCell(Sheet sheet, CellAddress address, String value) {
        Cell cell = sheet
            .getRow(address.rowNumber())
            .createCell(address.cellNumber());
        cell.setCellValue(value);
        cell.setCellStyle(defaultCellStyle.titleStyle());
    }

    private void fillHeaderCell(Sheet sheet, CellAddress address, Integer value) {
        Cell cell = sheet
            .getRow(address.rowNumber())
            .createCell(address.cellNumber());
        cell.setCellValue(value);
        cell.setCellStyle(defaultCellStyle.titleStyle());
    }
}
