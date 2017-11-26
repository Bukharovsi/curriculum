package ru.curriculum.api.error;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return null;
    }
}
