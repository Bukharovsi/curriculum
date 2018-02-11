package ru.curriculum.domain.stateSchedule.stateProgramFileParser;

import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.curriculum.domain.stateSchedule.dictionary.IDictionaryValuesFinder;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;

import java.io.IOException;
import java.util.*;

@Component
public class StateProgramFileParser {
    private Logger logger = LoggerFactory.getLogger(StateProgramFileParser.class);
    private StateProgramFieldComparator stateProgramFieldComparator;
    private StateProgramDateParser stateProgramDateParser;
    private StateProgramField stateProgramField;

    @Autowired
    public StateProgramFileParser(IDictionaryValuesFinder dictionaryValuesFinder) {
        this.stateProgramFieldComparator = new StateProgramFieldComparator();
        this.stateProgramDateParser = new StateProgramDateParser();
        this.stateProgramField = new StateProgramField(dictionaryValuesFinder);
    }

    public List<StateProgram> parse(MultipartFile file) {
        //TODO: проверка на расширение
        try {
            XWPFDocument doc = new XWPFDocument(file.getInputStream());
            List<StateProgram> programs = parseDoc(doc);
            return programs;
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Во время формирования плана-графика возникла ошибка. \n", e.toString()));
        }
    }

    private List<StateProgram> parseDoc(XWPFDocument doc) {
        List<StateProgram> stateProgramList = new ArrayList<>();
        Integer programYear = stateProgramDateParser.getStateProgramYear(doc);
        XWPFTable table = getTable(doc);
        if(null == table) {
            return stateProgramList;
        }
        List<XWPFTableRow> rows = getRows(table);
        Map<Integer, String> stateProgramFields = tryToIdentifyStateProgramFields(table);

        Date stateProgramBeginDate = stateProgramDateParser.makeStartDate(programYear, 1);
        Date stateProgramEndDate = stateProgramDateParser.makeEndDate(programYear, 1);
        for(XWPFTableRow row: rows) {
            if(1 == row.getTableCells().size()) {
                stateProgramBeginDate = stateProgramDateParser
                        .makeStartDate(programYear, stateProgramDateParser.getMonth(row.getCell(0).getText()));
                stateProgramEndDate = stateProgramDateParser
                        .makeEndDate(programYear, stateProgramDateParser.getMonth(row.getCell(0).getText()));
                continue;
            }

            StateProgram program = new StateProgram();
            program.dateStart(stateProgramBeginDate);
            program.dateFinish(stateProgramEndDate);

            List<XWPFTableCell> cells = row.getTableCells();
            for (int cellNumber = 0; cellNumber < cells.size(); cellNumber++) {
                if(stateProgramFields.containsKey(cellNumber)) {
                    Object value = cells.get(cellNumber).getText().isEmpty() ? null : cells.get(cellNumber).getText();
                    stateProgramField.addFieldToStateProgram(stateProgramFields.get(cellNumber), value, program);
                }
            }

            if(stateProgramTemplateNotEmpty(program)) {
                stateProgramList.add(program);
            }
        }

        return stateProgramList;
    }

    private List<XWPFTableRow> getRows(XWPFTable table) {
        return 1 < table.getNumberOfRows() ?
                table.getRows().subList(1, table.getNumberOfRows() - 1) : new ArrayList<>();
    }

    private XWPFTable getTable(XWPFDocument doc) {
        return 0 < doc.getTables().size() ? doc.getTables().get(0) : null;
    }

    private Map<Integer, String> tryToIdentifyStateProgramFields(XWPFTable table) {
        Map<Integer, String> stateProgramFields = new HashMap<>();
        if(0 == table.getRows().size()) {
            return stateProgramFields;
        }
        List<XWPFTableCell> cells = table.getRows().get(0).getTableCells();
        for(int cellNumber = 0; cellNumber < cells.size(); cellNumber++) {
            String fieldName = stateProgramFieldComparator.toFieldFormat(cells.get(cellNumber).getText());
            if(stateProgramFieldComparator.isStateProgramField(fieldName)) {
                stateProgramFields.put(cellNumber, fieldName);
            }
        }

        return stateProgramFields;
    }

    private boolean stateProgramTemplateNotEmpty(StateProgram stateProgram) {
        return !(null == stateProgram.name()
                && null == stateProgram.targetAudience()
                && null == stateProgram.implementationForm()
                && null == stateProgram.lernerCount()
                && null == stateProgram.groupCount()
                && null == stateProgram.countOfHoursPerLerner()
                && null == stateProgram.curator());
    }
}
