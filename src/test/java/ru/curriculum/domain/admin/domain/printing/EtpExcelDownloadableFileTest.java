package ru.curriculum.domain.admin.domain.printing;

import org.junit.Assert;
import org.junit.Test;
import ru.curriculum.domain.printing.file.excel.EtpExcelDownloadableFile;
import ru.curriculum.domain.printing.file.template.WorkbookGenerator;
import ru.curriculum.domain.printing.file.template.loader.TemplateLoaderFromResource;


public class EtpExcelDownloadableFileTest {

    @Test
    public void createDownloadableFile_mustCreateCorrectly() {
        EtpExcelDownloadableFile file = new EtpExcelDownloadableFile(
                new WorkbookGenerator(
                        new TemplateLoaderFromResource("etp.xls")
                ).createWorkbook()
        );

        Assert.assertNotNull(file);
        Assert.assertEquals("УТП.xls", file.name());
        Assert.assertEquals(".xls", file.format());
        Assert.assertTrue(0 < file.content().size());
    }

    @Test(expected = NullPointerException.class)
    public void tryToCreateEtpExcelFileFromNullWorkbook_mustBeNull() {
        new EtpExcelDownloadableFile(null);
    }
}
