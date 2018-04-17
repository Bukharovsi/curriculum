package ru.curriculum.domain.printing.file.template.specific;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

abstract class EtpFiller {

    protected final DefaultCellStyle defaultCellStyle;

    public EtpFiller(DefaultCellStyle defaultCellStyle) {
        this.defaultCellStyle = defaultCellStyle;
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
}
