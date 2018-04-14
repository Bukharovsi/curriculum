package ru.curriculum.domain.printing.file.template.loader;

import org.springframework.core.io.ClassPathResource;
import ru.curriculum.domain.printing.exception.TemplateLoadingException;

import java.io.File;
import java.io.IOException;

public class TemplateLoaderFromResource implements ITemplateLoader {
    private static final String TEMPLATE_PATH = "/printing/";
    private String templateFileName;

    public TemplateLoaderFromResource(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public File load() {
        try {
            return new ClassPathResource(getFullPathToTemplate()).getFile();
        } catch (IOException e) {
            throw new TemplateLoadingException(templateFileName, e);
        }
    }

    private String getFullPathToTemplate() {
        return TEMPLATE_PATH.concat(templateFileName);
    }
}
