package ru.curriculum.service.timetable;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressFindingService {
    private final List addresses = new ArrayList<String>(){{
        add("ул. Б.Красная, д. 68");
        add("ул. Социалистическая, д. 5");
        add("ул. Проточная, д. 8");
    }};

    public List<String> getAddresses() {
        return new ArrayList<>(addresses);
    }
}
