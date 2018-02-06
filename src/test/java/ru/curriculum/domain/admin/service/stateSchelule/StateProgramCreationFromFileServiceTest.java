package ru.curriculum.domain.admin.service.stateSchelule;

import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.*;

@Ignore
public class StateProgramCreationFromFileServiceTest {

    //TODO: точно прверить работу с .doc файлами
//    private String filename = "Положение_об обучении работников.docx";
    private String filename = "test.docx";

    @Test
    public void readDocFileTest() throws IOException {
        FileInputStream fis = new FileInputStream(new File(filename));
        XWPFDocument doc = new XWPFDocument(fis);

        List<XWPFTable> tables = doc.getTables();
        tables.get(0).getRows().forEach(row -> {
            row.getTableCells().forEach(cell -> {
                System.out.print(cell.getText() + " ");
            });
            System.out.println();
        });

        Assert.assertNotNull(doc);
    }
}
