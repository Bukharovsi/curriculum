package ru.curriculum.service.division;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.curriculum.domain.organization.repository.DivisionRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class DivisionFindingService {

    @Autowired
    private DivisionRepository divisionRepository;

    public Collection<DivisionDto> findAll() {
        ArrayList<DivisionDto> divisions = new ArrayList<>();
        divisionRepository.findAll().forEach(division -> divisions.add(new DivisionDto(division)));
        return divisions;
    }

}
