package ru.curriculum.service.printing;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.printing.file.IDownloadableFile;
import ru.curriculum.domain.printing.file.excel.ExcelDownloadableFile;
import ru.curriculum.domain.printing.file.template.specific.EtpWorkbookGenerator;
import ru.curriculum.domain.printing.file.template.WorkbookGenerator;
import ru.curriculum.domain.printing.file.template.loader.TemplateLoaderFromResource;
import ru.curriculum.domain.printing.file.template.specific.ETPTemplateCoordinates;
import ru.curriculum.service.etp.service.ETP_CRUDService;


@Component
public class EtpReportGenerationService implements IReportService {

    @Autowired
    private ETP_CRUDService etpCrudService;

    public IDownloadableFile createWorkbookForEtp(Integer etpId) {
        Workbook workbook = new EtpWorkbookGenerator(
                new WorkbookGenerator(new TemplateLoaderFromResource("etp.xls")),
                new ETPTemplateCoordinates(),
                etpCrudService.get(etpId)
        ).createWorkbook();

        return new ExcelDownloadableFile(workbook);
    }
}
