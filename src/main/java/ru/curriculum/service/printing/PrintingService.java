package ru.curriculum.service.printing;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.printing.file.ExcelFile;
import ru.curriculum.domain.printing.file.IFile;
import ru.curriculum.domain.printing.file.template.ITemplate;
import ru.curriculum.domain.printing.file.template.specific.ETPTemplate;
import ru.curriculum.domain.printing.file.template.Template;
import ru.curriculum.domain.printing.file.template.loader.TemplateLoaderFromResource;
import ru.curriculum.domain.printing.file.template.specific.ETPTemplateSubstitutionRule;
import ru.curriculum.service.etp.service.ETP_CRUDService;


@Component
public class PrintingService implements IPrintingService {
    @Autowired
    private ETP_CRUDService etpCrudService;

    public IFile getExcelFileWillBePrinted(Integer etpId) {
        Workbook workbook = new ETPTemplate(
                new Template(new TemplateLoaderFromResource("etp.xls")),
                new ETPTemplateSubstitutionRule(),
                etpCrudService.get(etpId)
        ).makeTemplate();

        return new ExcelFile(workbook);
    }
}
