package ru.curriculum.domain.admin.domain.printing;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.curriculum.domain.admin.domain.etp.ETPMock;
import ru.curriculum.domain.printing.file.template.IWorkbookGenerator;
import ru.curriculum.domain.printing.file.template.WorkbookGenerator;
import ru.curriculum.domain.printing.file.template.loader.TemplateLoaderFromResource;
import ru.curriculum.domain.printing.file.template.specific.EtpWorkbookGenerator;
import ru.curriculum.domain.printing.file.template.specific.ETPTemplateCoordinates;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Ignore
public class TemplateLoaderTest {
    private ETPMock etpMock;

    @Before
    public void setUp() {
        this.etpMock = new ETPMock();
    }

    @Test
    public void loadTemplate() throws IOException {
        IWorkbookGenerator etpTemplate = new EtpWorkbookGenerator(
                new WorkbookGenerator(
                        new TemplateLoaderFromResource("etp.xls")
                ),
                new ETPTemplateCoordinates(),
                etpMock.getETPDto()
        );

        Workbook workbook = etpTemplate.createWorkbook();

        Row row = workbook.getSheet("УТП").getRow(0);
        row.getCell(0);

        String cell = CellReference.convertNumToColString(1);

        FileOutputStream fos = new FileOutputStream(new File("etp.xls"));
        workbook.write(fos);
        workbook.close();
        fos.close();
    }
}
