package ru.curriculum.domain.printing.file.template;

import org.apache.poi.ss.usermodel.Workbook;

public class CalculatedTemplate implements ITemplate {
    private Template template;

    public CalculatedTemplate(Template template) {
        this.template = template;
    }

    @Override
    public Workbook makeTemplate() {
        // TODO: make some calculations
        return template.makeTemplate();
    }
}
