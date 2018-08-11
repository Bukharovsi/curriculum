package ru.curriculum.domain.stateSchedule.stateProgramFileParser;

import liquibase.util.file.FilenameUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.curriculum.domain.stateSchedule.dictionary.IDictionaryValuesFinder;
import ru.curriculum.domain.stateSchedule.entity.Internship;
import ru.curriculum.service.stateSchedule.service.FileParseResult;

import java.io.IOException;
import java.util.*;

@Component
public class StateProgramFileParser {
    private StateProgramFieldsStorage stateProgramFieldsStorage;
    private StateProgramDateParser stateProgramDateParser;
    private StateProgramFieldSetter fieldSetter;
    private ColumnIndexToFieldMapper columnIndexToFieldMapper;

    @Autowired
    public StateProgramFileParser(IDictionaryValuesFinder dictionaryValuesFinder) {
        this.stateProgramFieldsStorage = new StateProgramFieldsStorage();
        this.columnIndexToFieldMapper = new ColumnIndexToFieldMapper(stateProgramFieldsStorage);
        this.stateProgramDateParser = new StateProgramDateParser();
        this.fieldSetter = new StateProgramFieldSetter(dictionaryValuesFinder);
    }

    public FileParseResult parse(MultipartFile file) {
        FileParseResult result = new FileParseResult();
        if(file.isEmpty()) {
            result.addError("Невозможно сформировать \"План-график\" из пустого файла");
            return result;
        }

        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        if(!ext.equals("docx")) {
            result.addError("Допустимый формат: .docx");
            return result;
        }

        try {
            XWPFDocument doc = new XWPFDocument(file.getInputStream());
            List<StateProgramTemplate> programs = parseDoc(doc);
            result.setStateProgramTemplates(programs);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Во время формирования плана-графика возникла ошибка. %s \n", e.toString()));
        }
    }

    private List<StateProgramTemplate> parseDoc(XWPFDocument doc) {
        List<StateProgramTemplate> stateProgramList = new ArrayList<>();
        XWPFTable table = getTable(doc);
        if(null == table) {
            return stateProgramList;
        }

        List<XWPFTableRow> rows = getRows(table);
        ColumnIndexToFieldMap fieldMap = columnIndexToFieldMapper.tryToIdentifyFieldsAndMapToColumnIndex(table);

        Integer programYear = stateProgramDateParser.getStateProgramYear(doc);
        Date stateProgramBeginDate = stateProgramDateParser.makeStartDate(programYear, 1);
        Date stateProgramEndDate = stateProgramDateParser.makeEndDate(programYear, 1);
        for(XWPFTableRow row: rows) {
            //TODO: это непонятно! надо было сделать метод с говорящим названием
            if(1 == row.getTableCells().size()) {
                stateProgramBeginDate = stateProgramDateParser
                        .makeStartDate(programYear, stateProgramDateParser.getMonth(row.getCell(0).getText()));
                stateProgramEndDate = stateProgramDateParser
                        .makeEndDate(programYear, stateProgramDateParser.getMonth(row.getCell(0).getText()));
                continue;
            }

            StateProgramTemplate program = new StateProgramTemplate();
            Internship internship = new Internship();

            program.dateStart(stateProgramBeginDate);
            program.dateFinish(stateProgramEndDate);

            List<XWPFTableCell> cells = row.getTableCells();
            for (int cellNumber = 0; cellNumber < cells.size(); cellNumber++) {
                if(fieldMap.columnIsPresent(cellNumber)) {
                    String field = fieldMap.getField(cellNumber);
                    Object value = !cells.get(cellNumber).getText().isEmpty() ? cells.get(cellNumber).getText() : null;
                    if(stateProgramFieldsStorage.isInternshipField(field)) {
                        fieldSetter.setFieldToInternship(field, value, internship);
                    } else {
                        fieldSetter.setFieldToStateProgram(field, value, program);
                    }
                }
            }

            if(stateProgramTemplateNotEmpty(program)) {
                if(internshipNotEmpty(internship)) {
                    program.internships().add(internship);
                }
                stateProgramList.add(program);
            } else {
                if(internshipNotEmpty(internship)) {
                    if(null == internship.theme() || internship.theme().isEmpty()) {
                        internship.theme(getPreviousInternshipTheme(stateProgramList));
                    }
                    stateProgramList
                            .get(stateProgramList.size() - 1)
                            .internships()
                            .add(internship);
                }
            }
        }

        return stateProgramList;
    }

    private String getPreviousInternshipTheme(List<StateProgramTemplate> statePrograms) {
        String previousTheme = null;
        if(0 < statePrograms.size()) {
            List<Internship> internships = statePrograms
                    .get(statePrograms.size() - 1)
                    .internships();
            if(0 < internships.size()) {
                previousTheme = internships
                        .get(internships.size() - 1)
                        .theme();
            }
        }
        return previousTheme;
    }

    private List<XWPFTableRow> getRows(XWPFTable table) {
        return 1 < table.getNumberOfRows()
                ? table.getRows().subList(1, table.getNumberOfRows() - 1)
                : new ArrayList<>();
    }

    private XWPFTable getTable(XWPFDocument doc) {
        return 0 < doc.getTables().size() ? doc.getTables().get(0) : null;
    }

    private boolean stateProgramTemplateNotEmpty(StateProgramTemplate stateProgram) {
        return !(null == stateProgram.name()
                && null == stateProgram.targetAudience()
                && null == stateProgram.implementationForm()
                && null == stateProgram.lernerCount()
                && null == stateProgram.groupCount()
                && null == stateProgram.countOfHoursPerLerner()
                && null == stateProgram.responsibleDepartment()
                && null == stateProgram.address()
                && null == stateProgram.curator());
    }

    private boolean internshipNotEmpty(Internship internship) {
        return !(null == internship.theme()
                && null == internship.dateStart()
                && null == internship.dateFinish()
                && null == internship.address());
    }
}
