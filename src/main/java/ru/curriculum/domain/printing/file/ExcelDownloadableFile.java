package ru.curriculum.domain.printing.file;

import org.apache.poi.ss.usermodel.Workbook;
import ru.curriculum.domain.printing.exception.CreationFileException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ExcelDownloadableFile implements IDownloadableFile {
    private Workbook workbook;

    public ExcelDownloadableFile(Workbook workbook) {
        this.workbook = workbook;
    }

    @Override
    public ByteArrayOutputStream content() {
        ByteArrayOutputStream content = new ByteArrayOutputStream();
        try {
            workbook.write(content);
        } catch (IOException e) {
            // TODO: надо закрывать workbook в случае ошибки, но он кидает тоже ексепнш
            throw new CreationFileException("Can't create excel file");
        }

        return content;
    }

    @Override
    public String name() {
        return "УТП".concat(format());
    }

    @Override
    public String format() {
        return ".xls";
    }
}
