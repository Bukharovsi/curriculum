package ru.curriculum.domain.admin.domain.printing;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;
import ru.curriculum.domain.admin.domain.etp.ETPMock;
import ru.curriculum.domain.printing.file.template.ITemplate;
import ru.curriculum.domain.printing.file.template.Template;
import ru.curriculum.domain.printing.file.template.loader.TemplateLoaderFromResource;
import ru.curriculum.domain.printing.file.template.specific.ETPTemplate;
import ru.curriculum.domain.printing.file.template.specific.ETPTemplateSubstitutionRule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class TemplateLoaderTest {
    private ETPMock etpMock;

    @Before
    public void setUp() {
        this.etpMock = new ETPMock();
    }

    @Test
    public void loadTemplate() throws IOException {
        ITemplate etpTemplate = new ETPTemplate(
                new Template(
                        new TemplateLoaderFromResource("etp.xls")
                ),
                new ETPTemplateSubstitutionRule(),
                etpMock.getETPDto()
        );

        Workbook workbook = etpTemplate.makeTemplate();
        FileOutputStream fos = new FileOutputStream(new File("etp.xls"));
        workbook.write(fos);
        workbook.close();
        fos.close();
    }
}
