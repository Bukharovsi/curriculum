package ru.curriculum.domain.printing.file.template.specific;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import ru.curriculum.domain.printing.file.excel.CellAddress;
import ru.curriculum.domain.printing.file.excel.DefaultCellStyle;
import ru.curriculum.service.etp.dto.ETPDto;
import ru.curriculum.service.etp.dto.VolumeInHoursDto;

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

        if(null != etp.getVolumeInHours()) {
            VolumeInHoursDto volume = etp.getVolumeInHours();
            fillHeaderCell(sheet, tsr.vihTotal(), volume.getTotal());
            fillHeaderCell(sheet, tsr.vihPerOneListener(), volume.getDistancePerOneListener());
            fillHeaderCell(sheet, tsr.vihDistancePerOneListener(), volume.getDistancePerOneListener());
            fillHeaderCell(sheet, tsr.vihFullTimePerOneListener(), volume.getFullTimePerOneListener());
            fillHeaderCell(sheet, tsr.vihTotalStudyWork(), volume.getTotalStudyWork());
            fillHeaderCell(sheet, tsr.vihDistanceStudyWork(), volume.getDistanceStudyWork());
            fillHeaderCell(sheet, tsr.vihFullTimeStudyWork(), volume.getFullTimeStudyWork());
            fillHeaderCell(sheet, tsr.vihPaymentStudyWork(), volume.getPaymentStudyWork());
            fillHeaderCell(sheet, tsr.vihDistancePaymentForStudyWork(), volume.getDistancePaymentForStudyWork());
            fillHeaderCell(sheet, tsr.vihFullTimePaymentForStudyWork(), volume.getFullTimePaymentForStudyWork());
            fillHeaderCell(sheet, tsr.vihEmaPaymentForStudyWork(), volume.getEmaPaymentForStudyWork());
            fillHeaderCell(sheet, tsr.vihOmaPaymentForStudyWork(), volume.getOmaPaymentForStudyWork());
            fillHeaderCell(sheet, tsr.vihInLoad(), volume.getInLoad());
            fillHeaderCell(sheet, tsr.vihDistanceInLoad(), volume.getDistanceInLoad());
            fillHeaderCell(sheet, tsr.vihFullTimeInLoad(), volume.getFullTimeInLoad());
            fillHeaderCell(sheet, tsr.vihEmaInLoad(), volume.getEmaInLoad());
            fillHeaderCell(sheet, tsr.vihOmaInLoad(), volume.getOmaInLoad());
        }
    }

    private void fillHeaderCell(Sheet sheet, CellAddress address, String value) {
        Cell cell = sheet
            .getRow(address.rowNumber())
            .createCell(address.cellNumber());
        if(null != value) {
            cell.setCellValue(value);
        }
        cell.setCellStyle(defaultCellStyle.titleStyle());
    }

    private void fillHeaderCell(Sheet sheet, CellAddress address, Integer value) {
        Cell cell = sheet
            .getRow(address.rowNumber())
            .createCell(address.cellNumber());
        if(null != value) {
            cell.setCellValue(value);
        }
        cell.setCellStyle(defaultCellStyle.titleStyle());
    }

    private void fillHeaderCell(Sheet sheet, CellAddress address, Double value) {
        if(null == value) {
            return;
        }

        Cell cell = sheet
                .getRow(address.rowNumber())
                .createCell(address.cellNumber());
        cell.setCellValue(value);
        cell.setCellStyle(defaultCellStyle.titleStyle());
    }
}
