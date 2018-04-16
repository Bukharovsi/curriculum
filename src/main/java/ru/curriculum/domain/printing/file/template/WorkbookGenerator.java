package ru.curriculum.domain.printing.file.template;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import ru.curriculum.domain.printing.exception.CreationTemplateException;
import ru.curriculum.domain.printing.file.template.loader.ITemplateLoader;

import java.io.File;
import java.io.IOException;


public class WorkbookGenerator implements IWorkbookGenerator {
    private ITemplateLoader loader;

    public WorkbookGenerator(ITemplateLoader loader) {
        this.loader = loader;
    }

    public Workbook createWorkbook() {
        File file = loader.load();
        Workbook workbook;
        try {
            //TODO: надо правильно закрыть наверное в finally
            workbook = WorkbookFactory.create(file);
            return workbook;
        } catch (IOException | InvalidFormatException e) {
            throw new CreationTemplateException(e);
        }
    }
}
