package ru.curriculum.domain.printing.file.template;

import org.apache.poi.ss.usermodel.Workbook;

public interface IWorkbookGenerator {

    Workbook createWorkbook();
}
