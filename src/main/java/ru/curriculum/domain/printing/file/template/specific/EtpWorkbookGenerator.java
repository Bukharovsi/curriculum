package ru.curriculum.domain.printing.file.template.specific;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import ru.curriculum.domain.printing.file.template.IWorkbookGenerator;
import ru.curriculum.domain.printing.file.template.WorkbookGenerator;
import ru.curriculum.service.etp.dto.*;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class EtpWorkbookGenerator implements IWorkbookGenerator {
    private final WorkbookGenerator template;
    private final ETPDto etp;
    private final ETPTemplateCoordinates tsr;
    private DefaultCellStyle defaultCellStyle;


    public EtpWorkbookGenerator(WorkbookGenerator template, ETPTemplateCoordinates tsr, ETPDto etp) {
        this.template = template;
        this.etp = etp;
        this.tsr = tsr;
    }

    @Override
    public Workbook createWorkbook() {
        Workbook workbook = template.createWorkbook();
        this.defaultCellStyle = new DefaultCellStyle(workbook);

        EtpHeaderFiller headerFiller = new EtpHeaderFiller(etp, tsr, defaultCellStyle);
        EtpEmaFiller etpEmaFiller = new EtpEmaFiller(etp, tsr, defaultCellStyle);
        EtpOmaFiller etpOmaFiller = new EtpOmaFiller(etp, tsr, defaultCellStyle);
        EtpEaFiller etpEaFiller = new EtpEaFiller(etp, tsr, defaultCellStyle);
        EtpPivotFiller etpPivotFiller = new EtpPivotFiller(etp, tsr, defaultCellStyle);

        Sheet sheet = workbook.getSheet("УТП");
        Objects.requireNonNull(sheet, "Can`t find \"УТП\" sheet in workbook");

        headerFiller.fillHeaders(sheet);
        Integer emaModuleLastRow = etpEmaFiller.fillEmaModule(sheet);
        Integer omaModuleLastRow = etpOmaFiller.fillOmaModule(sheet, emaModuleLastRow);
        Integer eaModuleLastRow = etpEaFiller.fillEaModule(sheet, omaModuleLastRow);
        etpPivotFiller.fillPivotCell(sheet, eaModuleLastRow);

        return workbook;
    }






}
