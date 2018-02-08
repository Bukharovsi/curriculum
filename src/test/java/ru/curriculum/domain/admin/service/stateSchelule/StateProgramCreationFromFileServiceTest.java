package ru.curriculum.domain.admin.service.stateSchelule;

import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import ru.curriculum.service.stateSchedule.service.StateProgramFileParser;

@Ignore
public class StateProgramCreationFromFileServiceTest {

    //TODO: точно прверить работу с .doc файлами
//    private String filename = "Положение_об обучении работников.docx";
    private String filename = "test.docx";

    private StateProgramFileParser stateProgramFileParser;

    @Before
    public void setUp() throws Exception {
        stateProgramFileParser = new StateProgramFileParser();
    }

    @Test
    public void readDocFileTest() throws IOException {
        FileInputStream fis = new FileInputStream(new File(filename));
        stateProgramFileParser.parse(new File(filename));

        XWPFDocument doc = new XWPFDocument(fis);

//        List<XWPFTable> tables = doc.getTables();
//        tables.get(0).getRows().forEach(row -> {
//            row.getTableCells().forEach(cell -> {
//                System.out.print(cell.getText() + " ");
//            });
//            System.out.println();
//        });

        Assert.assertNotNull(doc);
    }
}
