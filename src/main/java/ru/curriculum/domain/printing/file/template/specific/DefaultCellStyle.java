package ru.curriculum.domain.printing.file.template.specific;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.poi.ss.usermodel.*;

@Getter
@Accessors(fluent = true)
public class DefaultCellStyle {
    private CellStyle titleStyle;
    private CellStyle mergedCellStyle;
    private CellStyle tableCellStyle;

    public DefaultCellStyle(Workbook workbook) {
        this.titleStyle = createTitleStyle(workbook);
        this.mergedCellStyle = createMergedCellStyle(workbook);
        this.tableCellStyle = createTableCellStyle(workbook);
    }

    private CellStyle createTableCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }


    private CellStyle createMergedCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.LEFT);
        return style;
    }

    private CellStyle createTitleStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
}
