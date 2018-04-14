package ru.curriculum.domain.printing.exception;

import java.io.IOException;

public class TemplateLoadingException extends RuntimeException {
    public TemplateLoadingException(String templateFlePath, IOException e) {
        super(String.format(
                "Can't load template file: \"%s\". Because %s",
                templateFlePath,
                e.getMessage()
        ));
    }
}
