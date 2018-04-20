package ru.curriculum.domain.printing.file.excel;

import org.apache.poi.ss.usermodel.Workbook;
import ru.curriculum.domain.printing.exception.CreationFileException;
import ru.curriculum.domain.printing.file.IDownloadableFile;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class EtpExcelDownloadableFile implements IDownloadableFile {
    private Workbook workbook;

    public EtpExcelDownloadableFile(Workbook workbook) {
        Objects.requireNonNull(workbook, "Workbook cannot be null");
        this.workbook = workbook;
    }

    @Override
    public ByteArrayOutputStream content() {
        ByteArrayOutputStream content = new ByteArrayOutputStream();
        try {
            workbook.write(content);
        } catch (Exception e) {
            // TODO: надо закрывать workbook в случае ошибки, но он кидает тоже ексепнш
            throw new CreationFileException(e);
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
