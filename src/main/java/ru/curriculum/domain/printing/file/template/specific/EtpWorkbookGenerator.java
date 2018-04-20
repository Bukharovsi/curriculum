package ru.curriculum.domain.printing.file.template.specific;

import org.apache.poi.ss.usermodel.*;
import ru.curriculum.domain.printing.file.excel.DefaultCellStyle;
import ru.curriculum.domain.printing.file.template.IWorkbookGenerator;
import ru.curriculum.service.etp.dto.*;

import java.util.Objects;

public class EtpWorkbookGenerator implements IWorkbookGenerator {
    private final IWorkbookGenerator template;
    private final ETPDto etp;
    private final ETPTemplateCoordinates tsr;

    public EtpWorkbookGenerator(IWorkbookGenerator template, ETPTemplateCoordinates tsr, ETPDto etp) {
        this.template = template;
        this.etp = etp;
        this.tsr = tsr;
    }

    @Override
    public Workbook createWorkbook() {
        Workbook workbook = template.createWorkbook();
        DefaultCellStyle cellStyle = new DefaultCellStyle(workbook);

        EtpHeaderFiller headerFiller = new EtpHeaderFiller(etp, tsr, cellStyle);
        EtpEmaFiller etpEmaFiller = new EtpEmaFiller(etp, tsr, cellStyle);
        EtpOmaFiller etpOmaFiller = new EtpOmaFiller(etp, tsr, cellStyle);
        EtpEaFiller etpEaFiller = new EtpEaFiller(etp, tsr, cellStyle);
        EtpPivotFiller etpPivotFiller = new EtpPivotFiller(etp, tsr, cellStyle);

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
