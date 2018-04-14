package ru.curriculum.service.printing;

import org.springframework.stereotype.Component;
import ru.curriculum.domain.printing.file.ExcelFile;
import ru.curriculum.domain.printing.file.IFile;
import ru.curriculum.domain.printing.file.template.CalculatedTemplate;
import ru.curriculum.domain.printing.file.template.Template;
import ru.curriculum.domain.printing.file.template.loader.TemplateLoaderFromResource;

@Component
public class PrintingService implements IPrintingService {

    public IFile getExcelFileWillBePrinted(String fileName) {
        return new ExcelFile(
                new CalculatedTemplate(
                        new Template(
                                new TemplateLoaderFromResource("etp.xls")
                        )
                ).makeTemplate()
        );
    }
}
