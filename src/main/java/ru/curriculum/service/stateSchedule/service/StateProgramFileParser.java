package ru.curriculum.service.stateSchedule.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class StateProgramFileParser {
    private Logger logger = LoggerFactory.getLogger(StateProgramFileParser.class);
    private StateProgramFieldComparator stateProgramFieldComparator;

    public StateProgramFileParser() {
        this.stateProgramFieldComparator = new StateProgramFieldComparator();
    }

    public StateProgram parse(File file) {
        /**
         * try to find date of document
         * try to find tables
         * try to find each tables
         *
         */
        try {
            XWPFDocument doc = new XWPFDocument(new FileInputStream(file));
            List<XWPFTable> tables = doc.getTables();
            if(tables.size() == 0) {
                logger.info("Can't find any tables");
                return null;
            }
            parseTables(tables.get(0));
        } catch (IOException e) {
            logger.error(e.toString());
        }

        /**
         * try to define state program field from table header
         *      each cell of first row compare with table column title
         *          if equals
         *              then add to field bucket
         * parse table body
         *      for each defined column extract value
         *
         *
         */

        return null;
    }

    // prototype
    private Map parseTables(XWPFTable table) {
        Map<Integer, String> stateProgramFields = tryToIdentifyStateProgramFields(table);
        List<Map<String, String>> programs = new ArrayList<>();
        if(1 < table.getRows().size()) {
            Date stateProgramBeginDate = new Date(1);
            Date stateProgramEndDate = new Date(1);
            for(XWPFTableRow row: table.getRows()) {
                if(1 == row.getTableCells().size()) {
                    stateProgramBeginDate = new Date();
                    stateProgramEndDate = new Date();
                    continue;
                }
                Map<String, String> stateProgram = new HashMap<>();
                stateProgram.put("beginDate", stateProgramBeginDate.toString());
                stateProgram.put("endDate", stateProgramEndDate.toString());
                List<XWPFTableCell> cells = row.getTableCells();
                for (int cellNumber = 0; cellNumber < cells.size(); cellNumber++) {
                    if(stateProgramFields.containsKey(new Integer(cellNumber))) {
                        stateProgram.put(stateProgramFields.get(cellNumber), cells.get(cellNumber).getText());
                    }
                }
                programs.add(stateProgram);
            }
        }

        return stateProgramFields;
    }

    private Map<Integer, String> tryToIdentifyStateProgramFields(XWPFTable table) {
        Map<Integer, String> stateProgramFields = new HashMap<>();
        if(0 == table.getRows().size()) {
            return stateProgramFields;
        }
        List<XWPFTableCell> cells = table.getRows().get(0).getTableCells();
        for(int cellNumber = 0; cellNumber < cells.size(); cellNumber++) {
            if(stateProgramFieldComparator.isStateProgramField(cells.get(cellNumber).getText())) {
                stateProgramFields.put(cellNumber, cells.get(cellNumber).getText());
            }
        }

        return stateProgramFields;
    }
}
