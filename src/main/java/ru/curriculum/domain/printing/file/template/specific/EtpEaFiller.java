package ru.curriculum.domain.printing.file.template.specific;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import ru.curriculum.service.etp.dto.*;

public class EtpEaFiller extends EtpFiller {

    private final ETPDto etp;
    private final ETPTemplateCoordinates tsr;
    private final DefaultCellStyle defaultCellStyle;

    public EtpEaFiller(ETPDto etp, ETPTemplateCoordinates tsr, DefaultCellStyle defaultCellStyle) {
        super(defaultCellStyle);
        this.etp = etp;
        this.tsr = tsr;
        this.defaultCellStyle = defaultCellStyle;
    }



    public Integer fillEaModule(Sheet sheet, Integer lastRow) {
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
