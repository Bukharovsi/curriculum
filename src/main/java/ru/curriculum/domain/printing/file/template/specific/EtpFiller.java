package ru.curriculum.domain.printing.file.template.specific;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import ru.curriculum.domain.printing.file.excel.DefaultCellStyle;
import ru.curriculum.service.teacher.dto.TeacherDto;

import java.util.List;

abstract class EtpFiller {

    protected String totalHoursFormula = "SUM(%s:%s)+%s*%s";

    protected String hoursPerOneListener = "SUM(%s:%s)";

    protected final DefaultCellStyle defaultCellStyle;

    protected final ETPTemplateCoordinates tsr;

    public EtpFiller(DefaultCellStyle defaultCellStyle, ETPTemplateCoordinates tsr) {
        this.defaultCellStyle = defaultCellStyle;
        this.tsr = tsr;
    }

    protected void createTableCell(Row row, Integer cellNumber, String value) {
        Cell cell = row.createCell(cellNumber);
        cell.setCellStyle(defaultCellStyle.tableCellStyle());
        cell.setCellValue(value);
    }

    protected void createTableCell(Row row, Integer cellNumber, Double value) {
        Cell cell = row.createCell(cellNumber);
        cell.setCellStyle(defaultCellStyle.tableCellStyle());
        cell.setCellValue(value);
    }

    protected void createTableCell(Row row, Integer cellNumber, Integer value) {
        Cell cell = row.createCell(cellNumber);
        cell.setCellStyle(defaultCellStyle.tableCellStyle());
        cell.setCellValue(value);
    }

    protected void createSubmoduleTableCell(Row row, Integer cellNumber, String value) {
        Cell cell = row.createCell(cellNumber);
        cell.setCellStyle(defaultCellStyle.mergedCellStyle());
        cell.setCellValue(value);
    }

    protected void createTotalHoursCell(Row row) {
        String startCellAddress = toCellAddress(row.getRowNum(), tsr.lectures());
        String endCellAddress = toCellAddress(row.getRowNum(), tsr.others());
        String standardCell = toCellAddress(row.getRowNum(), tsr.standard());
        String lernerCount = toCellAddress(row.getRowNum(), tsr.lernerCount());
        String formula = String.format(
                this.totalHoursFormula,
                startCellAddress,
                endCellAddress,
                standardCell,
                lernerCount
        );

        createCellFormula(row, tsr.totalHours(), formula);
    }

    protected void createHoursPerOneListenerFormula(Row row) {
        String startCellAddress = toCellAddress(row.getRowNum(), tsr.lectures());
        String endCellAddress = toCellAddress(row.getRowNum(), tsr.others());
        String formula = String.format(this.hoursPerOneListener, startCellAddress, endCellAddress);
        createCellFormula(row, tsr.hoursPerOneListener(), formula);
    }

    protected String joinTeachers(List<TeacherDto> teachers) {
        StringBuilder sb = new StringBuilder();
        for (TeacherDto teacher : teachers) {
            sb.append(teacher.getFullName()).append(" ");
        }
        return sb.toString();
    }

    protected void createCellFormula(Row row, Integer cellNum, String formula) {
        Cell cell = row.createCell(cellNum);
        cell.setCellType(CellType.FORMULA);
        cell.setCellStyle(defaultCellStyle.tableCellStyle());
        cell.setCellFormula(formula);
    }

    private String toCellAddress(Integer rowNum, Integer cellNum) {
        return CellReference.convertNumToColString(cellNum).concat(String.valueOf(rowNum + 1));
    }
}
