package ru.curriculum.domain.printing.file.template.specific;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import ru.curriculum.domain.printing.file.template.ITemplate;
import ru.curriculum.domain.printing.file.template.Template;
import ru.curriculum.service.etp.dto.*;

import java.text.SimpleDateFormat;

public class ETPTemplate implements ITemplate {
    private Template template;
    private ETPDto etp;
    private ETPTemplateSubstitutionRule tsr;
    private DefaultCellStyle defaultCellStyle;

    public ETPTemplate(Template template, ETPTemplateSubstitutionRule tsr, ETPDto etp) {
        this.template = template;
        this.etp = etp;
        this.tsr = tsr;
    }

    @Override
    public Workbook makeTemplate() {
        Workbook workbook = template.makeTemplate();
        this.defaultCellStyle = new DefaultCellStyle(workbook);

        Sheet sheet = workbook.getSheet("УТП");

        fillHeaders(sheet);
        Integer emaModuleLastRow = fillEmaModule(sheet);
        Integer omaModuleLastRow = fillOmaModule(sheet, emaModuleLastRow);
        Integer eaModuleLastRow = fillEaModule(sheet, omaModuleLastRow);
        fillPivotCell(sheet, eaModuleLastRow);

        return workbook;
    }

    private void fillPivotCell(Sheet sheet, Integer eaModuleLastRow) {
        fillTableCellPlansValue(sheet.getRow(eaModuleLastRow ), etp.getEmaModuleTotalRow());
        fillTableCellPlansValue(sheet.getRow(eaModuleLastRow + 1), etp.getOmaModuleTotalRow());
        fillTableCellPlansValue(sheet.getRow(eaModuleLastRow + 2), etp.getEaModuleTotalRow());
        fillTableCellPlansValue(sheet.getRow(eaModuleLastRow + 3), etp.getEtpTotalRow());
    }

    private void fillHeaders(Sheet sheet) {
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

    private Integer fillEaModule(Sheet sheet, Integer lastRow) {
        int countRow = 0;
        for (EAModuleDto module : etp.getEaModules()) {
            for (EASectionDto section : module.getSections()) {
                // number of topics and section theme
                countRow += section.getTopics().size() + 1;
            }
            // module theme
            countRow += 1;
        }

        sheet.shiftRows(
                lastRow,
                lastRow + tsr.offset(),
                countRow
        );

        int index = lastRow;

        int moduleIndex = 1;
        for (EAModuleDto module : etp.getEaModules()) {
            sheet.getRow(index).createCell(0).setCellValue(String.format("%s.", moduleIndex));
            fillMergedCell(index, sheet, module.getName());
            index++;

            int sectionIndex = 1;
            for (EASectionDto section : module.getSections()) {
                sheet.getRow(index).createCell(0).setCellValue(String.format("%s.%s.", moduleIndex, sectionIndex));
                fillMergedCell(index, sheet, section.getName());
                index++;

                int topicIndex = 1;
                for (EATopicDto topic : section.getTopics()) {
                    Row row = sheet.getRow(index);
                    row.createCell(0).setCellValue(String.format("%s.%s.%s.",moduleIndex, sectionIndex, topicIndex));
                    row.createCell(tsr.topicName()).setCellValue(topic.getName());
                    fillTableCellPlansValue(row, topic.getPlan());
                    topicIndex++;
                    index++;
                }
                sectionIndex++;
            }
            moduleIndex++;
        }

        return index;
    }

    private void fillMergedCell(Integer rowIndex, Sheet sheet, String value) {
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.createCell(1);
        cell.setCellValue(value);
        CellRangeAddress cellRangeAddress = new CellRangeAddress(
                row.getRowNum(),
                row.getRowNum(),
                1,
                tsr.teacher()
        );
        sheet.addMergedRegion(cellRangeAddress);
        cell.setCellStyle(defaultCellStyle.mergedCellStyle());
    }

    private Integer fillOmaModule(Sheet sheet, Integer lastRow) {
        sheet.shiftRows(
                lastRow,
                lastRow + tsr.offset(),
                etp.getOmaModules().size()
        );

        int index = lastRow;
        int rowNumber = lastRow + etp.getOmaModules().size();
        int moduleIndex = 1;

        for (OMAModuleDto module : etp.getOmaModules()) {
            Row row = sheet.getRow(index);
            row.createCell(0).setCellValue(String.format("%s.",moduleIndex));
            row.createCell(tsr.topicName()).setCellValue(module.getName());
            fillTableCellPlansValue(row, module.getPlan());
            moduleIndex++;
            index++;
        }

        return rowNumber + 1;
    }

    private Integer fillEmaModule(Sheet sheet) {
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
            row.createCell(0).setCellValue(String.format("%s.", moduleIndex));
            row.createCell(tsr.topicName()).setCellValue(module.getName());
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

    private void createTableCell(Row row, Integer cellNumber, String value) {
        Cell cell = row.createCell(cellNumber);
        cell.setCellStyle(defaultCellStyle.tableCellStyle());
        cell.setCellValue(value);
    }

    private void createTableCell(Row row, Integer cellNumber, Double value) {
        Cell cell = row.createCell(cellNumber);
        cell.setCellStyle(defaultCellStyle.tableCellStyle());
        cell.setCellValue(value);
    }

    private void createTableCell(Row row, Integer cellNumber, Integer value) {
        Cell cell = row.createCell(cellNumber);
        cell.setCellStyle(defaultCellStyle.tableCellStyle());
        cell.setCellValue(value);
    }
}
