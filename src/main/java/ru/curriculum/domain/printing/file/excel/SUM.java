package ru.curriculum.domain.printing.file.excel;


public class SUM implements ExcelFormula {
    private final String FORMULA_PATTERN = "SUM(%s:%s)";
    private final String cellAddressStart;
    private final String cellAddressEnd;

    public SUM(String cellAddressStart, String cellAddressEnd) {
        this.cellAddressStart = cellAddressStart;
        this.cellAddressEnd = cellAddressEnd;
    }

    @Override
    public String getFormula() {
        return String.format(FORMULA_PATTERN, cellAddressStart, cellAddressEnd);
    }
}
