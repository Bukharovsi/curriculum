package ru.curriculum.domain.admin.domain.printing;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.junit.Test;
import ru.curriculum.domain.printing.exception.CreationTemplateException;
import ru.curriculum.domain.printing.file.template.IWorkbookGenerator;
import ru.curriculum.domain.printing.file.template.WorkbookGenerator;
import ru.curriculum.domain.printing.file.template.loader.ITemplateLoader;
import ru.curriculum.domain.printing.file.template.loader.TemplateLoaderFromResource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class WorkbookGeneratorTest {

    @Test
    public void pathToGeneratorValidLoaderAndLoadFile_mustBeCreateCorrectly() {
        IWorkbookGenerator generator = new WorkbookGenerator(
                new TemplateLoaderFromResource("etp.xls")
        );
        Workbook workbook = generator.createWorkbook();

        Assert.assertNotNull(workbook);
    }

    @Test(expected = CreationTemplateException.class)
    public void createWorkbookFromFileWhichContentIsWrong_mustBeFailed() {
        IWorkbookGenerator generator = new WorkbookGenerator(getFakeLoaderWhichLoadNonEmptyFile());
        Workbook workbook = generator.createWorkbook();

        Assert.assertNotNull(workbook);
    }

    @Test(expected = CreationTemplateException.class)
    public void tryToCreateWorkbookFromEmptyFile_mustBeFail() {
        new WorkbookGenerator(getFakeLoaderWhichLoadEmptyFile())
                .createWorkbook();
    }

    @Test(expected = NullPointerException.class)
    public void pathToGeneratorNullLoader_mustBeFailed() {
        new WorkbookGenerator(null);
    }

    private ITemplateLoader getFakeLoaderWhichLoadEmptyFile() {
        return  () -> {
            File tmpFile = null;
            try {
                tmpFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".xls");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tmpFile;
        };
    }

    private ITemplateLoader getFakeLoaderWhichLoadNonEmptyFile() {
        return  () -> {
            File tmpFile = null;
            try {
                tmpFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".xls");
                BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile));
                bw.write("We emulate that this tmp file is real excel file with wrong content");
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tmpFile;
        };
    }
}
