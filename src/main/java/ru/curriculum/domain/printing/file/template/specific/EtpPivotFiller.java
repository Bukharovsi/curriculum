package ru.curriculum.domain.printing.file.template.specific;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import ru.curriculum.domain.printing.file.excel.DefaultCellStyle;
import ru.curriculum.domain.printing.file.excel.SUM;
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
        fill(sheet.getRow(eaModuleLastRow), getBeginRowForEmaModule(), getEndRowForEmaModule());
        fill(sheet.getRow(eaModuleLastRow + 1), getBeginRowForOmaModule(), getEndRowForOmaModule());
        fill(sheet.getRow(eaModuleLastRow + 2), getBeginRowForEaModule(), getEndRowForEaModule());
        fill(sheet.getRow(eaModuleLastRow + 3), getBeginRowForTotalRow(), getEndRowForTotalRow());
    }

    private void fill(Row row, Integer beginRow, Integer endRow) {
        createCellFormula(row, beginRow, endRow, tsr.lectures());
        createCellFormula(row, beginRow, endRow, tsr.practices());
        createCellFormula(row, beginRow, endRow, tsr.independentWorks());
        createCellFormula(row, beginRow, endRow, tsr.consultations());
        createCellFormula(row, beginRow, endRow, tsr.peerReviews());
        createCellFormula(row, beginRow, endRow, tsr.credits());
        createCellFormula(row, beginRow, endRow, tsr.hoursPerOneListener());
        createCellFormula(row, beginRow, endRow, tsr.others());
        createCellFormula(row, beginRow, endRow, tsr.standard());
        createCellFormula(row, beginRow, endRow, tsr.totalHours());
        createCellFormula(row, beginRow, endRow, tsr.lernerCount());
        createCellFormula(row, beginRow, endRow, tsr.groupCount());
        createCellFormula(row, beginRow, endRow, tsr.conditionalPagesCount());
    }

    private void createCellFormula(Row row, Integer beginRow, Integer endRow, Integer column) {
        Cell cell = row.createCell(column);
        cell.setCellType(CellType.FORMULA);
        cell.setCellStyle(defaultCellStyle.tableCellStyle());
        String formula = new SUM(createCellAddress(beginRow, column), createCellAddress(endRow, column)).getFormula();
        cell.setCellFormula(formula);
    }

    private String createCellAddress(Integer row, Integer column) {
        return CellReference.convertNumToColString(column).concat(row.toString());
    }

    private Integer getBeginRowForEmaModule() {
        return tsr.tableStartRow();
    }

    private Integer getEndRowForEmaModule() {
        return getBeginRowForEmaModule() + etp.getEmaModules().size();
    }

    private Integer getBeginRowForOmaModule() {
        return getEndRowForEmaModule() + 1;
    }

    private Integer getEndRowForOmaModule() {
        return getBeginRowForOmaModule() + etp.getOmaModules().size();
    }

    private Integer getBeginRowForEaModule() {
        return getEndRowForOmaModule() + 1;
    }

    private Integer getEndRowForEaModule() {
        int rowTotalCount = 0;
        for (EAModuleDto module : etp.getEaModules()) {
            for (EASectionDto section : module.getSections()) {
                // number of topics and section theme
                rowTotalCount += section.getTopics().size() + 1;
            }
            // module theme
            rowTotalCount += 1;
        }

        return getBeginRowForEaModule() + rowTotalCount;
    }

    private Integer getBeginRowForTotalRow() {
        return getEndRowForEaModule() + 1;
    }

    private Integer getEndRowForTotalRow() {
        return getBeginRowForTotalRow() + 2;
    }
}
