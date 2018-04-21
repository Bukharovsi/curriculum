package ru.curriculum.domain.printing.file.template.loader;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import ru.curriculum.domain.printing.exception.TemplateLoadingException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class TemplateLoaderFromResource implements ITemplateLoader {
    private static final String TEMPLATE_PATH = "/printing/";
    private String templateFileName;

    private final Logger log = LoggerFactory.getLogger(TemplateLoaderFromResource.class);

    public TemplateLoaderFromResource(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public File load() {

        try {
            File tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".xls");
            tempFile.deleteOnExit();
            InputStream fileAsInputStream = getClass().getResourceAsStream(getFullPathToTemplate());
            if(null == fileAsInputStream) {
                throw new TemplateLoadingException(templateFileName, "File not found ");
            }
            FileUtils.copyInputStreamToFile(fileAsInputStream, tempFile);
            return tempFile;
        } catch (IOException e) {
            log.warn("Can`t load template %s from jar by path %s, I will try to load from filesystem",
                    templateFileName, getFullPathToTemplate());
            try {
                log.info("Trying to get resource from file system");
                return new ClassPathResource(getFullPathToTemplate()).getFile();
            } catch (IOException ex) {
                throw new TemplateLoadingException(templateFileName, ex);
            }
        }
    }

    private String getFullPathToTemplate() {
        return TEMPLATE_PATH.concat(templateFileName);
    }
}
