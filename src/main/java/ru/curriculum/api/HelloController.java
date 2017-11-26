package ru.curriculum.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.curriculum.domain.Hello;
import ru.curriculum.domain.HelloRepository;

@RestController
public class HelloController {
    @Autowired
    private HelloRepository helloRepository;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String hello() {
        Hello hello = helloRepository.findOne(1);

        return hello.value();
    }
}
