package ru.curriculum.domain.stateSchedule.stateProgramFileParser;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import java.util.List;
import java.util.Optional;

class ColumnIndexToFieldMapper {
    private StateProgramFieldsStorage stateProgramFieldsStorage;

    ColumnIndexToFieldMapper(StateProgramFieldsStorage stateProgramFieldsStorage) {
        this.stateProgramFieldsStorage = stateProgramFieldsStorage;
    }

    ColumnIndexToFieldMap tryToIdentifyFieldsAndMapToColumnIndex(XWPFTable table) {
        ColumnIndexToFieldMap map = new ColumnIndexToFieldMap();
        if(0 == table.getRows().size()) {
            return map;
        }

        List<XWPFTableCell> cells = table.getRows().get(0).getTableCells();
        for(int cellNumber = 0; cellNumber < cells.size(); cellNumber++) {
            Optional<String> field = stateProgramFieldsStorage.getFieldIfPresent(cells.get(cellNumber).getText());
            if(field.isPresent()) {
                map.put(cellNumber, field.get());
            }
        }

        return map;
    }
}
